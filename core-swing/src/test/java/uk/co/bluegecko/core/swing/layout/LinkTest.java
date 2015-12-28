package uk.co.bluegecko.core.swing.layout;


import static org.junit.Assert.assertEquals;

import java.awt.Component;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class LinkTest
{

	@Test
	public final void testSingle()
	{
		final Component comp1 = new Spacer( 100, 20 );

		final Link[] links = Link.create( Direction.BOTH, comp1 );

		assertEquals( "list.size", 1, links.length );
		assertEquals( "1#comp", comp1, links[0].getComponent() );
		assertEquals( "1#preferred", comp1.getPreferredSize(), links[0].getPreferredSize() );
		assertEquals( "1#minimum", comp1.getPreferredSize(), links[0].getMinimumSize() );
		assertEquals( "1#maximum", comp1.getPreferredSize(), links[0].getMaximumSize() );
	}

	@Test
	public final void testBoth()
	{
		final Component comp1 = new Spacer( 100, 20 );
		final Component comp2 = new Spacer( 50, 50 );

		final Link[] links = Link.create( Direction.BOTH, comp1, comp2 );

		assertEquals( "list.size", 2, links.length );
		assertEquals( "1#comp", comp1, links[0].getComponent() );
		assertEquals( "1#preferred.width", comp1.getPreferredSize().width, links[0].getPreferredSize().width );
		assertEquals( "1#preferred.height", comp2.getPreferredSize().height, links[0].getPreferredSize().height );
		assertEquals( "2#comp", comp2, links[1].getComponent() );
		assertEquals( "2#preferred.width", comp1.getPreferredSize().width, links[1].getPreferredSize().width );
		assertEquals( "2#preferred.height", comp2.getPreferredSize().height, links[1].getPreferredSize().height );
	}

	@Test
	public final void testHorizontal()
	{
		final Component comp1 = new Spacer( 100, 20 );
		final Component comp2 = new Spacer( 50, 50 );

		final Link[] links = Link.create( Direction.HORIZONTAL, comp1, comp2 );

		assertEquals( "list.size", 2, links.length );
		assertEquals( "1#comp", comp1, links[0].getComponent() );
		assertEquals( "1#preferred.width", comp1.getPreferredSize().width, links[0].getPreferredSize().width );
		assertEquals( "1#preferred.height", comp1.getPreferredSize().height, links[0].getPreferredSize().height );
		assertEquals( "2#comp", comp2, links[1].getComponent() );
		assertEquals( "2#preferred.width", comp1.getPreferredSize().width, links[1].getPreferredSize().width );
		assertEquals( "2#preferred.height", comp2.getPreferredSize().height, links[1].getPreferredSize().height );
	}

	@Test
	public final void testVertical()
	{
		final Component comp1 = new Spacer( 100, 20 );
		final Component comp2 = new Spacer( 50, 50 );

		final Link[] links = Link.create( Direction.VERTICAL, comp1, comp2 );

		assertEquals( "list.size", 2, links.length );
		assertEquals( "1#comp", comp1, links[0].getComponent() );
		assertEquals( "1#preferred.width", comp1.getPreferredSize().width, links[0].getPreferredSize().width );
		assertEquals( "1#preferred.height", comp2.getPreferredSize().height, links[0].getPreferredSize().height );
		assertEquals( "2#comp", comp2, links[1].getComponent() );
		assertEquals( "2#preferred.width", comp2.getPreferredSize().width, links[1].getPreferredSize().width );
		assertEquals( "2#preferred.height", comp2.getPreferredSize().height, links[1].getPreferredSize().height );
	}

	@Test
	public final void testManager()
	{
		final Link.Manager manager = new Link.Manager( Direction.BOTH );
		final Component comp1 = new Spacer( 100, 20 );
		final Component comp2 = new Spacer( 50, 50 );

		final Link link1 = manager.link( comp1 );
		final Link link2 = manager.link( comp2 );

		assertEquals( "1#comp", comp1, link1.getComponent() );
		assertEquals( "1#preferred.width", comp1.getPreferredSize().width, link1.getPreferredSize().width );
		assertEquals( "1#preferred.height", comp2.getPreferredSize().height, link1.getPreferredSize().height );
		assertEquals( "2#comp", comp2, link2.getComponent() );
		assertEquals( "2#preferred.width", comp1.getPreferredSize().width, link2.getPreferredSize().width );
		assertEquals( "2#preferred.height", comp2.getPreferredSize().height, link2.getPreferredSize().height );

		manager.unlink( comp1 );
		assertEquals( "2.1#preferred.width", comp2.getPreferredSize().width, link2.getPreferredSize().width );
		assertEquals( "2.1#preferred.height", comp2.getPreferredSize().height, link2.getPreferredSize().height );
	}

}
