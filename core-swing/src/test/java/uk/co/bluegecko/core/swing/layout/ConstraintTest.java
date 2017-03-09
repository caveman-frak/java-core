/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;


public class ConstraintTest
{

	private Constraint tableConstraint;
	private Constraint columnConstraint;
	private Constraint rowConstraint;
	private Constraint cellConstraint;
	private Constraint[] constraints;

	@Before
	public void setUp() throws Exception
	{
		tableConstraint = new Constraint( new Insets( 10, 10, 10, 10 ), Alignment.WEST, Scale.NONE, Scale.NONE );
		columnConstraint = new Constraint( null, Alignment.EAST, null, null );
		rowConstraint = new Constraint( null, null, null, Scale.ALL );
		cellConstraint = new Constraint( null, null, new Scale( 0.5 ), null );
		constraints = new Constraint[]
			{ cellConstraint, rowConstraint, columnConstraint, tableConstraint };
	}

	@Test
	public final void testConstraint()
	{
		final Constraint constraint = new Constraint();
		assertEquals( Constraint.DEFAULT_INSETS, constraint.getInsets() );
		assertEquals( Constraint.DEFAULT_ALIGNMENT, constraint.getAlignment() );
		assertEquals( Constraint.DEFAULT_FILL, constraint.getHorizontal() );
		assertEquals( Constraint.DEFAULT_FILL, constraint.getVertical() );
	}

	@Test
	public final void testConstraintInsetsAlignmentDoubleDouble()
	{
		final Insets insets = new Insets( 10, 10, 10, 10 );
		final Alignment alignment = Alignment.CENTER;
		final double horizontal = 0.25;
		final double vertical = 0.75;
		final Constraint constraint = new Constraint( insets, alignment, horizontal, vertical );
		assertEquals( insets, constraint.getInsets() );
		assertEquals( alignment, constraint.getAlignment() );
		assertEquals( horizontal, constraint.getHorizontal()
				.getFactor(), 0.001 );
		assertEquals( vertical, constraint.getVertical()
				.getFactor(), 0.001 );
	}

	@Test
	public final void testConstraintInsetsAlignmentFillFill()
	{
		final Insets insets = new Insets( 10, 10, 10, 10 );
		final Alignment alignment = Alignment.CENTER;
		final Scale horizontal = Scale.ALL;
		final Scale vertical = new Scale( 0.5 );
		final Constraint constraint = new Constraint( insets, alignment, horizontal, vertical );
		assertEquals( insets, constraint.getInsets() );
		assertEquals( alignment, constraint.getAlignment() );
		assertEquals( horizontal, constraint.getHorizontal() );
		assertEquals( vertical, constraint.getVertical() );
	}

	@Test
	public final void testEvaluateConstraints()
	{
		final Constraint constraint = Constraint.evaluateConstraints( tableConstraint, columnConstraint, rowConstraint,
				cellConstraint );
		assertEquals( new Insets( 10, 10, 10, 10 ), constraint.getInsets() );
		assertEquals( Alignment.EAST, constraint.getAlignment() );
		assertEquals( new Scale( 0.5d ), constraint.getHorizontal() );
		assertEquals( new Scale( 1.0d ), constraint.getVertical() );
	}

	@Test
	public final void testRollUpInsets()
	{
		assertEquals( new Insets( 10, 10, 10, 10 ), Constraint.rollUpInsets( constraints ) );
	}

	@Test
	public final void testRollUpAlignment()
	{
		assertEquals( Alignment.EAST, Constraint.rollUpAlignment( constraints ) );
	}

	@Test
	public final void testRollUpHorizontal()
	{
		assertEquals( new Scale( 0.5d ), Constraint.rollUpHorizontal( constraints ) );
	}

	@Test
	public final void testRollUpVertical()
	{
		assertEquals( new Scale( 1.0d ), Constraint.rollUpVertical( constraints ) );
	}

	@Test
	public final void testApplyInsets()
	{
		final Rectangle r1 = new Rectangle( new Point( 10, 10 ), new Dimension( 100, 20 ) );
		final Constraint constraint = new Constraint( new Insets( 5, 5, 5, 5 ), Alignment.SOUTHEAST );

		final Rectangle r2 = constraint.applyInsets( r1 );

		assertEquals( "x", 15, r2.x );
		assertEquals( "y", 15, r2.y );
		assertEquals( "width", 90, r2.width );
		assertEquals( "height", 10, r2.height );
	}

	@Test
	public final void testCalculateBounds()
	{
		final Rectangle r1 = new Rectangle( new Point( 10, 10 ), new Dimension( 200, 50 ) );
		final Component component = new Spacer( 100, 20 );
		final Constraint constraint = new Constraint( new Insets( 5, 5, 5, 5 ), Alignment.SOUTHEAST );

		final Rectangle r2 = constraint.calculateBounds( r1, component );

		assertEquals( "x", 105, r2.x );
		assertEquals( "y", 35, r2.y );
		assertEquals( "width", 100, r2.width );
		assertEquals( "height", 20, r2.height );
	}

	@Test
	public final void testCalculateBoundsWithFill()
	{
		final Rectangle r1 = new Rectangle( new Point( 10, 10 ), new Dimension( 200, 50 ) );
		final Component component = new Spacer( 100, 20 );
		final Constraint constraint = new Constraint( new Insets( 5, 5, 5, 5 ), Alignment.SOUTHEAST, Scale.ALL,
				Scale.NONE );

		final Rectangle r2 = constraint.calculateBounds( r1, component );

		assertEquals( "x", 15, r2.x );
		assertEquals( "y", 35, r2.y );
		assertEquals( "width", 190, r2.width );
		assertEquals( "height", 20, r2.height );
	}

}
