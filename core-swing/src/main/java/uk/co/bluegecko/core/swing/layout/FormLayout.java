/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SizeSequence;


/**
 * A form based layout manager.
 */
public class FormLayout implements LayoutManager2
{

	private final List< Component > components = new ArrayList< >();
	private final List< Constraint > constraints = new ArrayList< >();
	private int columns;
	private int padX;
	private int padY;

	/**
	 * Create a new layout with the specified number of columns and padding.
	 * 
	 * @param columns
	 *            number of columns
	 * @param padX
	 *            horizontal padding
	 * @param padY
	 *            y vertical padding
	 */
	public FormLayout( final int columns, final int padX, final int padY )
	{
		super();

		this.columns = columns;
		this.padX = padX;
		this.padY = padY;
	}

	/**
	 * Create a new layout with the specified number of columns and padding.
	 * 
	 * @param columns
	 *            number of columns
	 */
	public FormLayout( final int columns )
	{
		this( columns, 0, 0 );
	}

	/**
	 * Set the number of columns for layout.
	 * 
	 * @param columns
	 *            number of columns
	 */
	public void setColumns( final int columns )
	{
		this.columns = columns;
	}

	/**
	 * Set the number of columns for layout.
	 * 
	 * @return number of columns
	 */
	public int getColumnCount()
	{
		return columns;
	}

	/**
	 * Get the current row count, based on number of columns and contained components.
	 * 
	 * @return number of rows
	 */
	public int getRowCount()
	{
		return components.size() / getColumnCount() + ( components.size() % getColumnCount() > 0 ? 1 : 0 );
	}

	protected int getPadX()
	{
		return padX;
	}

	protected void setPadX( final int padX )
	{
		this.padX = padX;
	}

	protected int getPadY()
	{
		return padY;
	}

	protected void setPadY( final int padY )
	{
		this.padY = padY;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager2#addLayoutComponent(java.awt.Component, java.lang.Object)
	 */
	@Override
	public synchronized void addLayoutComponent( final Component component, final Object constraint )
	{
		synchronized ( component.getTreeLock() )
		{
			components.add( component );
			constraints.add( ( Constraint ) constraint );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager2#getLayoutAlignmentX(java.awt.Container)
	 */
	@Override
	public float getLayoutAlignmentX( final Container alignX )
	{
		return 0.5f;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager2#getLayoutAlignmentY(java.awt.Container)
	 */
	@Override
	public float getLayoutAlignmentY( final Container alignY )
	{
		return 0.5f;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager2#invalidateLayout(java.awt.Container)
	 */
	@Override
	public void invalidateLayout( final Container container )
	{
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
	 */
	@Override
	public void addLayoutComponent( final String constraint, final Component component )
	{
		addLayoutComponent( component, null );
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	@Override
	public void layoutContainer( final Container container )
	{
		synchronized ( container.getTreeLock() )
		{
			final SizeSequence columnSizes = calculateColumnSizes( Size.PREFERRED );
			final SizeSequence rowSizes = calculateRowSizes( Size.PREFERRED );
			final Insets insets = container.getInsets();
			for ( int i = 0; i < components.size(); i++ )
			{
				final int column = i % columns;
				final int row = i / columns;
				final Point origin = new Point( columnSizes.getPosition( column ) + insets.left + padX * column,
						rowSizes.getPosition( row ) + insets.top + padY * row );
				final Dimension size = new Dimension( columnSizes.getSize( column ), rowSizes.getSize( row ) );
				final Rectangle bounds = new Rectangle( origin, size );
				final Constraint constraint = getConstraint( row, column );

				final Component component = components.get( i );
				component.setBounds( constraint.calculateBounds( bounds, component ) );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager2#maximumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension maximumLayoutSize( final Container container )
	{
		return calculateLayoutSize( Size.MAX, container );
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension minimumLayoutSize( final Container container )
	{
		return calculateLayoutSize( Size.MIN, container );
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	@Override
	public Dimension preferredLayoutSize( final Container container )
	{
		return calculateLayoutSize( Size.PREFERRED, container );
	}

	protected Dimension calculateLayoutSize( final Size size, final Container container )
	{
		synchronized ( container.getTreeLock() )
		{
			final SizeSequence columnSizes = calculateColumnSizes( size );
			final SizeSequence rowSizes = calculateRowSizes( size );
			final Insets insets = container.getInsets();

			final int width = columnSizes.getPosition( getColumnCount() ) + columnSizes.getSize( getColumnCount() )
					+ insets.left + insets.right + padX * ( columns - 1 );

			final int height = rowSizes.getPosition( getRowCount() ) + rowSizes.getSize( getRowCount() ) + insets.top
					+ insets.bottom + padY * ( getRowCount() - 1 );

			return new Dimension( width, height );
		}
	}

	protected SizeSequence calculateColumnSizes( final Size size )
	{
		final SizeSequence columns = new SizeSequence( getColumnCount() );

		int index = 0;
		for ( final Component component : components )
		{
			final int row = index / getColumnCount();
			final int column = index % getColumnCount();
			final Constraint constraint = getConstraint( row, column );

			final int width = size.getSize( component ).width + constraint.getInsets().left
					+ constraint.getInsets().right;
			columns.setSize( column, Math.max( columns.getSize( column ), width ) );
			index++;
		}
		return columns;
	}

	protected SizeSequence calculateRowSizes( final Size size )
	{
		final SizeSequence rows = new SizeSequence( getRowCount() );

		int index = 0;
		for ( final Component component : components )
		{
			final int row = index / getColumnCount();
			final int column = index % getColumnCount();
			final Constraint constraint = getConstraint( row, column );

			final int height = size.getSize( component ).height + constraint.getInsets().top
					+ constraint.getInsets().bottom;
			rows.setSize( row, Math.max( rows.getSize( row ), height ) );
			index++;
		}
		return rows;
	}

	protected Constraint getConstraint( final int row, final int column )
	{
		final Constraint constraint = constraints.get( column + row * columns );
		return constraint == null ? Constraint.DEFAULT : constraint;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	@Override
	public void removeLayoutComponent( final Component component )
	{
		synchronized ( component.getTreeLock() )
		{
			final int index = components.indexOf( component );
			if ( index > -1 )
			{
				components.remove( index );
				constraints.remove( index );
			}
		}
	}

	/**
	 * Create a new layout using the passed parameters.
	 * 
	 * @param constraint
	 *            default constraint for all components
	 * @param columns
	 *            number of columns
	 * @param padX
	 *            horizontal padding
	 * @param padY
	 *            vertical padding
	 * @param components
	 *            set of components to add
	 * @return layout manager
	 */
	public static JPanel createPane( final Constraint constraint, final int columns, final int padX, final int padY,
			final Component... components )
	{
		final JPanel panel = new JPanel( new FormLayout( columns, padX, padY ) );

		for ( final Component component : components )
		{
			panel.add( component, constraint );
		}
		return panel;
	}

	/**
	 * Create a new layout using the passed parameters.
	 * 
	 * @param columns
	 *            number of columns
	 * @param components
	 *            set of components to add
	 * @return layout manager
	 */
	public static JPanel createPane( final int columns, final Component... components )
	{
		return createPane( null, columns, 0, 0, components );
	}

}
