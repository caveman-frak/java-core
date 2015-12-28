/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SizeSequence;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class FormLayoutTest
{

	private FormLayout layout;
	private JPanel panel;
	private Component component1;
	private Component component2;
	private Component component3;
	private Component component4;
	private Component component5;
	private Component component6;
	private Constraint constraint4;

	/**
	 * 15 pixel space between components
	 * border of 5
	 * 1st column 100
	 * 2nd column 420 (400 + insets of 10 left and right)
	 * 1st row 20
	 * 2nd row 25
	 * 3rd row 120 (100 + insets of 10 top and bottom)
	 */
	@Before
	public void setUp() throws Exception
	{
		layout = new FormLayout( 2, 5, 5 );
		panel = new JPanel( layout );
		panel.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
		panel.add( component1 = new Spacer( 100, 15 ), new Constraint( Alignment.NORTHWEST ) );
		panel.add( component2 = new Spacer( 180, 20 ), new Constraint( Alignment.WEST ) );
		panel.add( component3 = new Spacer( 80, 15 ), new Constraint( new Insets( 0, 5, 0, 5 ), Alignment.SOUTHWEST ) );
		panel.add( component4 = new Spacer( 140, 25 ),
				constraint4 = new Constraint( new Insets( 0, 5, 0, 5 ), Alignment.EAST, Scale.NONE, Scale.ALL ) );
		panel.add( component5 = new Spacer( 0, 0 ),
				new Constraint( new Insets( 0, 0, 0, 0 ), Alignment.WEST, Scale.ALL, Scale.ALL ) );
		panel.add( component6 = new Spacer( 400, 100 ),
				new Constraint( new Insets( 10, 10, 10, 10 ), Alignment.WEST ) );
	}

	@Test
	public final void testGetColumnCount()
	{
		assertEquals( 2, layout.getColumnCount() );
	}

	@Test
	public final void testGetRowCount()
	{
		assertEquals( 3, layout.getRowCount() );
	}

	@Test
	public final void testGetLayoutAlignmentX()
	{
		assertEquals( 0.5, layout.getLayoutAlignmentX( panel ), 0.001 );
	}

	@Test
	public final void testGetLayoutAlignmentY()
	{
		assertEquals( 0.5, layout.getLayoutAlignmentY( panel ), 0.001 );
	}

	@Test
	public final void testCalculateColumnSize()
	{
		final SizeSequence sequence = layout.calculateColumnSizes( Size.MAX );
		assertEquals( "length", 2, sequence.getSizes().length );
		assertEquals( "1st", 100, sequence.getSizes()[0] );
		assertEquals( "2nd", 420, sequence.getSizes()[1] );
		assertEquals( "width", 520, sequence.getPosition( 1 ) + sequence.getSize( 1 ) );
	}

	@Test
	public final void testCalculateRowSizeMAX()
	{
		final SizeSequence sequence = layout.calculateRowSizes( Size.MAX );
		assertEquals( "length", 3, sequence.getSizes().length );
		assertEquals( "1st", 20, sequence.getSizes()[0] );
		assertEquals( "2nd", 25, sequence.getSizes()[1] );
		assertEquals( "2nd", 120, sequence.getSizes()[2] );
		assertEquals( "height", 165, sequence.getPosition( 2 ) + sequence.getSize( 2 ) );
	}

	@Test
	public final void testCalculateLayoutSizeMAX()
	{
		final Dimension dim = layout.calculateLayoutSize( Size.MAX, panel );
		assertEquals( "width", 545, dim.width );
		assertEquals( "height", 195, dim.height );
	}

	@Test
	public final void testCalculateLayoutSizeMIN()
	{
		final Dimension dim = layout.calculateLayoutSize( Size.MIN, panel );
		assertEquals( "width", 545, dim.width );
		assertEquals( "height", 195, dim.height );
	}

	@Test
	public final void testCalculateLayoutSizePREFERRED()
	{
		final Dimension dim = layout.calculateLayoutSize( Size.PREFERRED, panel );
		assertEquals( "width", 545, dim.width );
		assertEquals( "height", 195, dim.height );
	}

	@Test
	public final void testMaximumLayoutSize()
	{
		final int width = 10 + 100 + 5 + 420 + 10;
		final int height = 10 + 20 + 5 + 25 + 5 + 120 + 10;
		assertEquals( new Dimension( width, height ), layout.maximumLayoutSize( panel ) );
	}

	@Test
	public final void testMinimumLayoutSize()
	{
		final int width = 10 + 100 + 5 + 420 + 10;
		final int height = 10 + 20 + 5 + 25 + 5 + 120 + 10;
		assertEquals( new Dimension( width, height ), layout.minimumLayoutSize( panel ) );
	}

	@Test
	public final void testPreferredLayoutSize()
	{
		final int width = 10 + 100 + 5 + 420 + 10;
		final int height = 10 + 20 + 5 + 25 + 5 + 120 + 10;
		assertEquals( new Dimension( width, height ), layout.preferredLayoutSize( panel ) );
	}

	@Test
	public final void testGetConstraint()
	{
		final Constraint constraint = layout.getConstraint( 1, 1 );
		assertSame( "constraint 4", constraint4, constraint );
		assertEquals( "insets", new Insets( 0, 5, 0, 5 ), constraint.getInsets() );
		assertEquals( "alignment", Alignment.EAST, constraint.getAlignment() );
		assertEquals( "alignment", Scale.NONE, constraint.getHorizontal() );
		assertEquals( "alignment", Scale.ALL, constraint.getVertical() );
	}

	@Test
	public final void testRemoveLayoutComponent()
	{
		layout.removeLayoutComponent( component6 );
		layout.removeLayoutComponent( component5 );
		assertEquals( 2, layout.getColumnCount() );
		assertEquals( 2, layout.getRowCount() );
		final Dimension dim = layout.calculateLayoutSize( Size.MAX, panel );
		assertEquals( "width", 305, dim.width );
		assertEquals( "height", 70, dim.height );
	}

	@Test
	public final void testLayoutContainer()
	{
		layout.layoutContainer( panel );
		assertEquals( "1st", new Rectangle( new Point( 10, 10 ), new Dimension( 100, 15 ) ), component1.getBounds() );
		assertEquals( "2nd", new Rectangle( new Point( 115, 10 ), new Dimension( 180, 20 ) ), component2.getBounds() );
		assertEquals( "3rd", new Rectangle( new Point( 15, 45 ), new Dimension( 80, 15 ) ), component3.getBounds() );
		assertEquals( "4th", new Rectangle( new Point( 390, 35 ), new Dimension( 140, 25 ) ), component4.getBounds() );
		assertEquals( "5th", new Rectangle( new Point( 10, 65 ), new Dimension( 100, 120 ) ), component5.getBounds() );
		assertEquals( "6th", new Rectangle( new Point( 125, 75 ), new Dimension( 400, 100 ) ), component6.getBounds() );
	}

}
