package uk.co.bluegecko.core.collections.tree;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class TreeTest
{

	private Tree< String > tree;

	/**
	 * Create the following tree:
	 * 
	 * <pre>
	 *        C    E
	 *       /   /
	 *     B - D
	 *   /   \   \
	 * A       G   F
	 *   \
	 *     H
	 * </pre>
	 */
	@Before
	public void setUp()
	{
		tree = TreeBuilder.tree( "A" ).add( "B", "H" );
		tree.get( "B" ).add( "C", "D", "G" );
		tree.get( "B", "D" ).add( "E", "F" );
	}

	@Test
	public final void testCtor()
	{
		assertThat( tree.value(), is( "A" ) );
		assertThat( tree.get( "B" ).value(), is( "B" ) );
		assertThat( tree.get( "B" ).get( "C" ).value(), is( "C" ) );
	}

	@Test
	public final void testEquals()
	{
		assertThat( tree, is( tree ) );
		assertThat( tree, is( TreeBuilder.tree( "A" ) ) );
		assertThat( tree.hashCode(), is( TreeBuilder.tree( "A" ).hashCode() ) );
	}

	@Test
	public final void testNotEquals()
	{
		assertThat( tree, is( not( "A" ) ) );
		assertThat( tree, is( not( TreeBuilder.tree( "B" ) ) ) );
	}

	@Test
	public final void testChildrenOfB()
	{
		final Tree< String > nodeB = tree.get( "B" );
		assertThat( nodeB.children(), hasSize( 3 ) );
		assertThat( nodeB.isLeaf(), is( false ) );
	}

	@Test
	public final void testChildrenOfC()
	{
		final Tree< String > nodeB = tree.get( "B", "C" );
		assertThat( nodeB.children(), hasSize( 0 ) );
		assertThat( nodeB.isLeaf(), is( true ) );
	}

	@Test
	public final void testPathOneExists()
	{
		assertThat( tree.get( "B" ).value(), is( "B" ) );
	}

	@Test
	public final void testPathTwoExists()
	{
		assertThat( tree.get( "B", "C" ).value(), is( "C" ) );
	}

	@Test
	public final void testPathOneEmpty()
	{
		assertThat( tree.get( "A" ), is( nullValue() ) );
	}

	@Test
	public final void testPathTwoEmpty()
	{
		assertThat( tree.get( "B", "E" ), is( nullValue() ) );
	}

	@Test
	public final void testDepthFirst()
	{
		final Iterator< Tree< String > > it = tree.depthFirst();
		assertThat( it.next().value(), is( "A" ) );
		assertThat( it.next().value(), is( "B" ) );
		assertThat( it.next().value(), is( "C" ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.next().value(), is( "D" ) );
		assertThat( it.next().value(), is( "E" ) );
		assertThat( it.next().value(), is( "F" ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.next().value(), is( "G" ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.next().value(), is( "H" ) );
		assertThat( it.hasNext(), is( false ) );
	}

	@Test
	public final void testBreadthFirst()
	{
		final Iterator< Tree< String > > it = tree.breadthFirst();
		assertThat( it.next().value(), is( "A" ) );
		assertThat( it.next().value(), is( "B" ) );
		assertThat( it.next().value(), is( "H" ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.next().value(), is( "C" ) );
		assertThat( it.next().value(), is( "D" ) );
		assertThat( it.next().value(), is( "G" ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.next().value(), is( "E" ) );
		assertThat( it.hasNext(), is( true ) );
		assertThat( it.next().value(), is( "F" ) );
		assertThat( it.hasNext(), is( false ) );
	}

}
