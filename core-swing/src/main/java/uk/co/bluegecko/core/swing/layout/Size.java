/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;

import java.awt.Component;
import java.awt.Dimension;

/**
 * Interface containing static implementations used to get the specific size from a component (e.g. maximum, minimum or
 * preferred)
 */
public interface Size
{

	/**
	 * Return the specified size for the passed component.
	 * 
	 * @param component
	 *            - the component to return the size of.
	 * @return - the specified size.
	 */
	public Dimension getSize( Component component );

	/**
	 * Static implementation for returning the minimum size of a component.
	 */
	public static final Size MIN = new Size()
	{

		@Override
		public Dimension getSize( Component component )
		{
			return component.getMinimumSize();
		}

	};

	/**
	 * Static implementation for returning the preferred size of a component.
	 */
	public static final Size PREFERRED = new Size()
	{

		@Override
		public Dimension getSize( Component component )
		{
			return component.getPreferredSize();
		}

	};

	/**
	 * Static implementation for returning the maximum size of a component.
	 */
	public static final Size MAX = new Size()
	{

		@Override
		public Dimension getSize( Component component )
		{
			return component.getMaximumSize();
		}

	};

	/**
	 * Static implementation for returning the size of a component.
	 */
	public static final Size SIZE = new Size()
	{

		@Override
		public Dimension getSize( Component component )
		{
			return component.getSize();
		}

	};

}
