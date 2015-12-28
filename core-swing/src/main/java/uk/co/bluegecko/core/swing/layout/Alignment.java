/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Enumeration for determining the position of a component within its grid.
 */
public enum Alignment
{
	/**
	 * Aligned top and centre.
	 */
	NORTH
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = mid( container.x, container.width, component.width );
			int y = start( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned top and right.
	 */
	NORTHEAST
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = end( container.x, container.width, component.width );
			int y = start( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned centre and right.
	 */
	EAST
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = end( container.x, container.width, component.width );
			int y = mid( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned bottom and right.
	 */
	SOUTHEAST
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = end( container.x, container.width, component.width );
			int y = end( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned bottom and centre.
	 */
	SOUTH
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = mid( container.x, container.width, component.width );
			int y = end( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned bottom and left.
	 */
	SOUTHWEST
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = start( container.x, container.width, component.width );
			int y = end( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned centre and left.
	 */
	WEST
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = start( container.x, container.width, component.width );
			int y = mid( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned top and left.
	 */
	NORTHWEST
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = start( container.x, container.width, component.width );
			int y = start( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	},
	/**
	 * Aligned centre and centre.
	 */
	CENTER
	{
		@Override
		public Rectangle getBounds( Rectangle container, Dimension component )
		{
			int x = mid( container.x, container.width, component.width );
			int y = mid( container.y, container.height, component.height );
			return new Rectangle( x, y, component.width, component.height );
		}
	};

	/**
	 * Return the bounding rectangle for the component to ensure it is position correctly within the containing cell.
	 * 
	 * @param container
	 *            - the bounds of the containing cell.
	 * @param component
	 *            - the bounds of the component.
	 * @return - the adjusted bounds of the component.
	 */
	public abstract Rectangle getBounds( Rectangle container, Dimension component );

	/**
	 * Calculate the starting position of the component, for left or top alignment.
	 * 
	 * @param start1
	 *            - starting position (x or y) of container.
	 * @param length1
	 *            - width or height of the container.
	 * @param length2
	 *            - width or height of the component.
	 * @return - adjusted starting position (x or y) or the component.
	 */
	protected static final int start( int start1, int length1, int length2 )
	{
		return start1;
	}

	/**
	 * Calculate the starting position of the component, for centre alignment.
	 * 
	 * @param start1
	 *            - starting position (x or y) of container.
	 * @param length1
	 *            - width or height of the container.
	 * @param length2
	 *            - width or height of the component.
	 * @return - adjusted starting position (x or y) or the component.
	 */
	protected static final int mid( int start1, int length1, int length2 )
	{
		return start1 + ( length1 - length2 ) / 2;
	}

	/**
	 * Calculate the starting position of the component, for right or bottom alignment.
	 * 
	 * @param start1
	 *            - starting position (x or y) of container.
	 * @param length1
	 *            - width or height of the container.
	 * @param length2
	 *            - width or height of the component.
	 * @return - adjusted starting position (x or y) or the component.
	 */
	protected static final int end( int start1, int length1, int length2 )
	{
		return start1 + length1 - length2;
	}

}
