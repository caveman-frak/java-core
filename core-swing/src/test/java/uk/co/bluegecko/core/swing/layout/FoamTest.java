package uk.co.bluegecko.core.swing.layout;


import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

import org.junit.Test;


public class FoamTest
{

	private Foam spacer;
	private final Dimension size = new Dimension( 100, 20 );
	private final Dimension origin = new Dimension( 0, 0 );

	@Test
	public final void testSpacerCtor_IntsNoBias()
	{
		spacer = new Foam( 100, 20 );
		assertEquals( "minimum", origin, spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 50, 10 ), spacer.getPreferredSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_IntsWithBias()
	{
		spacer = new Foam( 100, 20, 0.25f );
		assertEquals( "minimum", origin, spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 25, 5 ), spacer.getPreferredSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_OneDimensionNoBias()
	{
		spacer = new Foam( size );
		assertEquals( "minimum", origin, spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 50, 10 ), spacer.getPreferredSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_OneDimensionWithBias()
	{
		spacer = new Foam( size, 0.75f );
		assertEquals( "minimum", origin, spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 75, 15 ), spacer.getPreferredSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_TwoDimensionNoBias()
	{
		spacer = new Foam( new Dimension( 20, 10 ), size );
		assertEquals( "minimum", new Dimension( 20, 10 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 60, 15 ), spacer.getPreferredSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_TwoDimensionWithBias()
	{
		spacer = new Foam( new Dimension( 20, 10 ), size, 0.75f );
		assertEquals( "minimum", new Dimension( 20, 10 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 80, 18 ), spacer.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 100, 20 ), spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_ThreeDimension()
	{
		spacer = new Foam( new Dimension( 20, 10 ), new Dimension( 30, 15 ), size );
		assertEquals( "minimum", new Dimension( 20, 10 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 30, 15 ), spacer.getPreferredSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testScaleUp()
	{
		spacer = new Foam( size );
		final Foam adjusted = spacer.scale( 1.5f );
		assertEquals( "minimum", origin, adjusted.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 75, 15 ), adjusted.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 150, 30 ), adjusted.getMaximumSize() );
	}

	@Test
	public final void testScaleDown()
	{
		spacer = new Foam( size );
		final Foam adjusted = spacer.scale( 0.5f );
		assertEquals( "minimum", origin, adjusted.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 25, 5 ), adjusted.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 50, 10 ), adjusted.getMaximumSize() );
	}

	@Test
	public final void testBias()
	{
		spacer = new Foam( size );
		final Foam adjusted = spacer.bias( 0.75f );
		assertEquals( "minimum", origin, adjusted.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 75, 15 ), adjusted.getPreferredSize() );
		assertEquals( "maximum", size, adjusted.getMaximumSize() );
	}

	@Test( expected = AssertionError.class )
	public final void testAssertMinPrefHeight()
	{
		spacer = new Foam( new Dimension( 10, 0 ), new Dimension( 0, 0 ), new Dimension( 20, 20 ) );
		assertEquals( "minimum", new Dimension( 10, 0 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 0, 0 ), spacer.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 20, 20 ), spacer.getMaximumSize() );
	}

	@Test( expected = AssertionError.class )
	public final void testAssertMinPrefWidth()
	{
		spacer = new Foam( new Dimension( 0, 10 ), new Dimension( 0, 0 ), new Dimension( 20, 20 ) );
		assertEquals( "minimum", new Dimension( 0, 10 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 0, 0 ), spacer.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 20, 20 ), spacer.getMaximumSize() );
	}

	@Test( expected = AssertionError.class )
	public final void testAssertPrefMaxHeight()
	{
		spacer = new Foam( new Dimension( 10, 0 ), new Dimension( 20, 30 ), new Dimension( 20, 20 ) );
		assertEquals( "minimum", new Dimension( 0, 0 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 20, 30 ), spacer.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 20, 20 ), spacer.getMaximumSize() );
	}

	@Test( expected = AssertionError.class )
	public final void testAssertPrefMaxWidth()
	{
		spacer = new Foam( new Dimension( 0, 0 ), new Dimension( 30, 20 ), new Dimension( 20, 20 ) );
		assertEquals( "minimum", new Dimension( 0, 0 ), spacer.getMinimumSize() );
		assertEquals( "preferred", new Dimension( 30, 20 ), spacer.getPreferredSize() );
		assertEquals( "maximum", new Dimension( 20, 20 ), spacer.getMaximumSize() );
	}

}
