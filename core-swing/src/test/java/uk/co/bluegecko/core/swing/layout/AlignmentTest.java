/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import java.awt.Dimension;
import java.awt.Rectangle;

import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class AlignmentTest
{

	private static Rectangle ZERO_CONTAINER = new Rectangle( 0, 0, 200, 300 );
	private static Rectangle OFFSET_CONTAINER = new Rectangle( 40, 160, 200, 300 );
	private static Dimension COMPONENT = new Dimension( 80, 120 );

	@Test
	public final void testGetBounds_NORTH()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.NORTH.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 60, bounds.x );
		Assert.assertEquals( 0, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.NORTH.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 100, bounds.x );
		Assert.assertEquals( 160, bounds.y );
	}

	@Test
	public final void testGetBounds_NORTHEAST()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.NORTHEAST.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 120, bounds.x );
		Assert.assertEquals( 0, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.NORTHEAST.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 160, bounds.x );
		Assert.assertEquals( 160, bounds.y );
	}

	@Test
	public final void testGetBounds_EAST()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.EAST.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 120, bounds.x );
		Assert.assertEquals( 90, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.EAST.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 160, bounds.x );
		Assert.assertEquals( 250, bounds.y );
	}

	@Test
	public final void testGetBounds_SOUTHEAST()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.SOUTHEAST.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 120, bounds.x );
		Assert.assertEquals( 180, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.SOUTHEAST.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 160, bounds.x );
		Assert.assertEquals( 340, bounds.y );
	}

	@Test
	public final void testGetBounds_SOUTH()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.SOUTH.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 60, bounds.x );
		Assert.assertEquals( 180, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.SOUTH.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 100, bounds.x );
		Assert.assertEquals( 340, bounds.y );
	}

	@Test
	public final void testGetBounds_SOUTHWEST()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.SOUTHWEST.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 0, bounds.x );
		Assert.assertEquals( 180, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.SOUTHWEST.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 40, bounds.x );
		Assert.assertEquals( 340, bounds.y );
	}

	@Test
	public final void testGetBounds_WEST()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.WEST.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 0, bounds.x );
		Assert.assertEquals( 90, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.WEST.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 40, bounds.x );
		Assert.assertEquals( 250, bounds.y );
	}

	@Test
	public final void testGetBounds_NORTHWEST()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.NORTHWEST.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 0, bounds.x );
		Assert.assertEquals( 0, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.NORTHWEST.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 40, bounds.x );
		Assert.assertEquals( 160, bounds.y );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.framework.layout.Alignment.CENTER#getBounds(java.awt.Rectangle, java.awt.Rectangle)}.
	 * Component should be positioned center / center.
	 */
	@Test
	public final void testGetBounds_CENTER()
	{
		// test with zero container and zero component
		Rectangle bounds = Alignment.CENTER.getBounds( ZERO_CONTAINER, COMPONENT );
		Assert.assertEquals( 60, bounds.x );
		Assert.assertEquals( 90, bounds.y );
		Assert.assertEquals( 80, bounds.width );
		Assert.assertEquals( 120, bounds.height );
		// test with offset container and zero component
		bounds = Alignment.CENTER.getBounds( OFFSET_CONTAINER, COMPONENT );
		Assert.assertEquals( 100, bounds.x );
		Assert.assertEquals( 250, bounds.y );
	}

}
