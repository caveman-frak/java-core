package uk.co.bluegecko.core.collections.tree;


import java.util.Iterator;
import java.util.List;


/**
 * A node for a tree.
 *
 * @author tim
 *
 * @param <T>
 *            type of value for the tree
 */
public interface Tree< T > extends Leaf< T >
{

	/**
	 * The children of this node.
	 *
	 * @return the children
	 */
	public List< Tree< T > > children();

	/**
	 * Add children to this node.
	 *
	 * @param children
	 *            the children to add
	 * @return the current node
	 */
	@SuppressWarnings( "unchecked" )
	public Tree< T > add( final T... children );

	/**
	 * Add child nodes to this node.
	 *
	 * @param children
	 *            the nodes to add
	 * @return the current node
	 */
	@SuppressWarnings( "unchecked" )
	public Tree< T > add( final Tree< T >... children );

	/**
	 * Remove a node from the tree.
	 *
	 * @param node
	 *            the node to remove
	 * @return the current node
	 */
	public Tree< T > remove( Tree< T > node );

	/**
	 * Retrieve the child node with a path of the passed values.
	 *
	 * @param values
	 *            the path of values to find
	 * @return the node with the value, or null if it does not exist
	 */
	@SuppressWarnings( "unchecked" )
	public Tree< T > get( T... values );

	/**
	 * Return a depth first iterator for the tree.
	 *
	 * @return the iterator
	 */
	public Iterator< Tree< T > > depthFirst();

	/**
	 * Return a breadth first iterator for the tree.
	 *
	 * @return the iterator
	 */
	public Iterator< Tree< T > > breadthFirst();

}
