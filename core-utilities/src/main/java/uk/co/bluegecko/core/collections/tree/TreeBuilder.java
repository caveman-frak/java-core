package uk.co.bluegecko.core.collections.tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.MutablePair;


/**
 * Basic implementation of tree nodes.
 *
 * @author tim
 *
 */
public class TreeBuilder
{

	protected static class TreeLeaf< T > implements Leaf< T >
	{

		private final Tree< T > parent;
		private final T value;

		protected TreeLeaf( final T value, final Tree< T > parent )
		{
			this.value = value;
			this.parent = parent;
		}

		@Override
		public Tree< T > parent()
		{
			return parent;
		}

		@Override
		public T value()
		{
			return value;
		}

		@Override
		public boolean isLeaf()
		{
			return true;
		}

		@Override
		public String toString()
		{
			return new StringBuilder().append( "[" ).append( "value=" ).append( value ).append( "]" ).toString();
		}

		@Override
		public int hashCode()
		{
			return value.hashCode();
		}

		@Override
		public boolean equals( final Object other )
		{
			if ( this == other )
			{
				return true;
			}
			if ( other == null || !( other instanceof Leaf ) )
			{
				return false;
			}
			return value.equals( ( ( Leaf< ? > ) other ).value() );
		}

	}

	protected static class TreeNode< T > extends TreeLeaf< T >implements Tree< T >
	{

		private final List< Tree< T > > children;

		protected TreeNode( final T value, final Tree< T > parent )
		{
			super( value, parent );

			this.children = new ArrayList< >();
		}

		protected TreeNode( final Leaf< T > leaf )
		{
			this( leaf.value(), leaf.parent() );
		}

		@Override
		public boolean isLeaf()
		{
			return children.isEmpty();
		}

		@Override
		public List< Tree< T > > children()
		{
			return Collections.unmodifiableList( children );
		}

		@Override
		@SuppressWarnings( "unchecked" )
		public Tree< T > add( final T... children )
		{
			final List< Tree< T > > leaves = new ArrayList< >();
			for ( final T child : children )
			{
				leaves.add( new TreeNode< >( child, this ) );
			}
			return add( leaves );
		}

		@Override
		@SuppressWarnings( "unchecked" )
		public Tree< T > add( final Tree< T >... children )
		{
			return add( Arrays.asList( children ) );
		}

		protected Tree< T > add( final List< Tree< T > > children )
		{
			this.children.addAll( children );

			return this;
		}

		@Override
		public Tree< T > remove( final Tree< T > node )
		{
			children.remove( node );
			return this;
		}

		@SuppressWarnings( "unchecked" )
		@Override
		public Tree< T > get( final T... values )
		{
			final T value = values[0];
			for ( final Tree< T > child : children )
			{
				if ( child.value().equals( value ) )
				{
					if ( values.length == 1 )
					{
						return child;
					}
					else
					{
						return child.get( Arrays.copyOfRange( values, 1, values.length ) );
					}
				}
			}
			return null;
		}

		@Override
		public Iterator< Tree< T > > depthFirst()
		{
			return new DepthFirstIterator< >( this );
		}

		@Override
		public Iterator< Tree< T > > breadthFirst()
		{
			return new BreadthFirstIterator< >( this );
		}

		protected Tree< T > replace( final Leaf< T > leaf, final Tree< T > node )
		{
			final int index = children.indexOf( leaf );

			children.remove( index );
			children.add( index, node );

			return node;
		}

	}

	/**
	 * Create a new empty tree.
	 *
	 * @param value
	 *            value for root node
	 * @return the root node
	 */
	public static < T > Tree< T > tree( final T value )
	{
		return new TreeNode< >( value, null );
	}

	/**
	 * Create a new empty tree.
	 *
	 * @param value
	 *            value for root node
	 * @param children
	 *            the child nodes to add to the tree
	 * @return the root node
	 */
	@SafeVarargs
	public static < T > Tree< T > tree( final T value, final T... children )
	{
		return new TreeNode< >( value, null ).add( children );
	}

	private static abstract class TreeIterator< T > implements Iterator< Tree< T > >
	{

		protected static final class TreePair< T > extends MutablePair< Tree< T >, Integer >
		{

			private static final long serialVersionUID = 4646204781543209202L;

			public TreePair( final Tree< T > tree, final int index )
			{
				super( tree, index );
			}

		}

		private final Function< Deque< TreePair< T > >, TreePair< T > > get;
		private final Consumer< Deque< TreePair< T > > > remove;
		private final Deque< TreePair< T > > deque;

		protected TreeIterator( final Tree< T > tree, final int index,
				final Function< Deque< TreePair< T > >, TreePair< T > > get,
				final Consumer< Deque< TreePair< T > > > remove )
		{
			this.get = get;
			this.remove = remove;
			deque = new ArrayDeque< >();
			deque.push( pair( tree, index ) );
		}

		protected TreePair< T > pair( final Tree< T > tree, final int index )
		{
			return new TreePair< >( tree, index );
		}

		protected TreePair< T > pair( final Tree< T > tree )
		{
			return pair( tree, 0 );
		}

		protected Deque< TreePair< T > > deque()
		{
			return deque;
		}

		@Override
		public boolean hasNext()
		{
			if ( deque().isEmpty() )
			{
				return false;
			}
			else
			{
				final TreePair< T > pair = get.apply( deque() );
				final Tree< T > node = pair.getLeft();
				final Integer index = pair.getRight();

				if ( index >= node.children().size() )
				{
					remove.accept( deque() );
					return hasNext();
				}
			}
			return true;
		}

	}

	private static final class DepthFirstIterator< T > extends TreeIterator< T >
	{

		protected DepthFirstIterator( final Tree< T > tree )
		{
			super( tree, 0, ( final Deque< TreePair< T > > q ) -> q.getFirst(),
					( final Deque< TreePair< T > > q ) -> q.removeFirst() );
		}

		@Override
		public Tree< T > next()
		{
			final TreePair< T > pair = deque().getFirst();
			final Tree< T > node = pair.getLeft();
			final int index = pair.getRight();
			pair.setRight( index + 1 );

			if ( index > node.children().size() )
			{
				deque().removeFirst();
				return next();
			}
			else if ( index < node.children().size() )
			{
				deque().push( pair( node.children().get( index ) ) );
			}

			// only return this node if we are still on the first index
			if ( index == 0 )
			{
				return node;
			}
			// otherwise skip to the top of the stack again
			else
			{
				return next();
			}
		}

	}

	private static final class BreadthFirstIterator< T > extends TreeIterator< T >
	{

		protected BreadthFirstIterator( final Tree< T > tree )
		{
			super( tree, -1, ( final Deque< TreePair< T > > q ) -> q.getLast(),
					( final Deque< TreePair< T > > q ) -> q.removeLast() );
		}

		@Override
		public Tree< T > next()
		{
			final TreePair< T > pair = deque().getLast();
			final Tree< T > node = pair.getLeft();
			int index = pair.getRight();

			// return this node (used for first node)
			if ( index == -1 )
			{
				pair.setRight( ++index );
				return node;
			}
			// skip up the stack
			else if ( index >= node.children().size() )
			{
				deque().removeLast();
				return next();
			}
			// return current child
			else
			{
				final Tree< T > child = node.children().get( index );
				if ( !child.isLeaf() )
				{
					deque().addFirst( pair( child ) );
				}
				pair.setRight( ++index );
				return child;
			}

		}

	}

}
