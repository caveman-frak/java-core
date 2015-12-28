package uk.co.bluegecko.core.swing.components;


import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serializable;

import uk.co.bluegecko.core.util.Maths;


/**
 * Helper class for sizing components. Hooks into the componentResized events and ensures that any new size does not
 * breach the requested max and min sizes.
 */
public class SizedHelper extends Object implements Serializable
{

	private static final long serialVersionUID = -384256590355880452L;

	/** Constant to indicate using preferred size for passed dimension */
	public static final int USE_PREFERRED_SIZE = -1;
	/** Constant to indicate using unrestricted size for passed dimension */
	public static final int UNRESTRICTED_SIZE = -2;

	/** The component being helped. */
	private final Sized sized;
	/** The size is being calculated, blocks recursion. */
	private transient boolean calculating = false;

	/**
	 * Construct a SizeHelper for the passed component.
	 *
	 * @param sized
	 *            component to help
	 */
	public SizedHelper( final Sized sized )
	{
		super();

		this.sized = sized;
		sized.addComponentListener( new ComponentAdapter()
		{

			@Override
			public void componentResized( final ComponentEvent e )
			{
				notifySizeChanged();
			}
		} );
	}

	/** Fix the size of the component, ensuring it obeys the settings for max and min size. */
	public final synchronized void notifySizeChanged()
	{
		if ( !calculating )
		{
			try
			{
				calculating = true;

				final Dimension size = new Dimension( sized.getSize() );
				final Dimension adjusted = adjustSize( size );

				// if the dimensions have changed then apply the adjusted size
				if ( !size.equals( adjusted ) )
				{
					sized.setSize( adjusted );
				}
			}
			finally
			{
				calculating = false;
			}
		}
	}

	/**
	 * Return the size, adjusting for max and min size settings.
	 *
	 * @param size
	 *            the requested size
	 * @return the adjusted size
	 */
	public final synchronized Dimension adjustSize( final Dimension size )
	{
		Dimension result = size;

		// adjust for minimum size settings
		final Dimension minimumSize = getCorrectedMinimumSize();
		result = Maths.max( result, minimumSize );

		// adjust for maximum size settings
		final Dimension maximumSize = getCorrectedMaximumSize();
		result = Maths.min( result, maximumSize );

		return result;
	}

	/**
	 * Return the minimum size, adjusting for preferred size settings.
	 *
	 * @return the minimum size
	 */
	public final synchronized Dimension getCorrectedMinimumSize()
	{
		final Dimension minimumSize = sized.getMinimumSize();
		final Dimension preferredSize = sized.getPreferredSize();
		final int boundary = 0;

		return new Dimension(
				corrected( minimumSize != null ? minimumSize.width : boundary,
						preferredSize != null ? preferredSize.width : boundary, boundary ),
				corrected( minimumSize != null ? minimumSize.height : boundary,
						preferredSize != null ? preferredSize.height : boundary, boundary ) );
	}

	/**
	 * Return the maximum size, adjusting for preferred size settings.
	 *
	 * @return the maximum size
	 */
	public final synchronized Dimension getCorrectedMaximumSize()
	{
		final Dimension maximumSize = sized.getMaximumSize();
		final Dimension preferredSize = sized.getPreferredSize();
		final int boundary = Integer.MAX_VALUE;

		return new Dimension(
				corrected( maximumSize != null ? maximumSize.width : boundary,
						preferredSize != null ? preferredSize.width : boundary, boundary ),
				corrected( maximumSize != null ? maximumSize.height : boundary,
						preferredSize != null ? preferredSize.height : boundary, boundary ) );
	}

	private int corrected( final int size, final int preferred, final int boundary )
	{
		int result = size;
		if ( size == UNRESTRICTED_SIZE )
		{
			result = boundary;
		}
		else if ( size == USE_PREFERRED_SIZE )
		{
			result = preferred;
		}
		return result;
	}

	/**
	 * Create a dimension that is a a fixed width, and unrestricted height.
	 *
	 * @param width
	 *            the requested width
	 * @return the calculated dimension
	 */
	public static Dimension widthBound( final int width )
	{
		return new Dimension( width, UNRESTRICTED_SIZE );
	}

	/**
	 * Create a dimension that is a fixed height, and unrestricted width.
	 *
	 * @param height
	 *            the requested height
	 * @return the calculated dimension
	 */
	public static Dimension heightBound( final int height )
	{
		return new Dimension( UNRESTRICTED_SIZE, height );
	}

	/**
	 * Create a dimension that is a fixed width, and uses preferred height.
	 *
	 * @param width
	 *            the requested width
	 * @return the calculated dimension
	 */
	public static Dimension heightPreferred( final int width )
	{
		return new Dimension( width, USE_PREFERRED_SIZE );
	}

	/**
	 * Create a dimension that is unrestricted width, and uses preferred height.
	 *
	 * @return the calculated dimension
	 */
	public static Dimension heightPreferred()
	{
		return new Dimension( UNRESTRICTED_SIZE, USE_PREFERRED_SIZE );
	}

	/**
	 * Create a dimension that is a fixed height, and uses preferred width.
	 *
	 * @param height
	 *            * the requested height
	 * @return the calculated dimension
	 */
	public static Dimension widthPreferred( final int height )
	{
		return new Dimension( USE_PREFERRED_SIZE, height );
	}

	/**
	 * Create a dimension that is unrestricted height, and uses preferred width.
	 *
	 * @return the calculated dimension
	 */
	public static Dimension widthPreferred()
	{
		return new Dimension( USE_PREFERRED_SIZE, UNRESTRICTED_SIZE );
	}

	/**
	 * Create a dimension that uses preferred height and width.
	 *
	 * @return the calculated dimension
	 */
	public static Dimension preferred()
	{
		return new Dimension( USE_PREFERRED_SIZE, USE_PREFERRED_SIZE );
	}

	/**
	 * Create a dimension that has unrestricted height and width.
	 *
	 * @return the calculated dimension
	 */
	public static Dimension unrestricted()
	{
		return new Dimension( UNRESTRICTED_SIZE, UNRESTRICTED_SIZE );
	}

}
