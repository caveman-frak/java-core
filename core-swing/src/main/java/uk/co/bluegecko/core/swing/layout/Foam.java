package uk.co.bluegecko.core.swing.layout;


import java.awt.Dimension;

import javax.swing.JPanel;


/**
 * Space filler, will expand to fit.
 *
 */
public class Foam extends JPanel
{

	private static final long serialVersionUID = 3791529173439691858L;

	private final Dimension minSize;
	private final Dimension preferredSize;
	private final Dimension maxSize;

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param minSize
	 *            minimum size to use
	 * @param preferredSize
	 *            preferred size to use
	 * @param maxSize
	 *            maximum size to use
	 */
	public Foam( final Dimension minSize, final Dimension preferredSize, final Dimension maxSize )
	{
		super();

		assert preferredSize.height >= minSize.height;
		assert maxSize.height >= preferredSize.height;
		assert preferredSize.width >= minSize.width;
		assert maxSize.width >= preferredSize.width;

		this.minSize = minSize;
		this.preferredSize = preferredSize;
		this.maxSize = maxSize;
	}

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param minSize
	 *            minimum size to use
	 * @param maxSize
	 *            maximum size to use
	 * @param bias
	 *            calculate preferred size as a proportion between min and max sizes
	 */
	public Foam( final Dimension minSize, final Dimension maxSize, final float bias )
	{
		this( minSize, new Dimension( minSize.width + Math.round( ( maxSize.width - minSize.width ) * bias ),
				minSize.height + Math.round( ( maxSize.height - minSize.height ) * bias ) ), maxSize );
	}

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param minSize
	 *            minimum size to use
	 * @param maxSize
	 *            maximum size to use
	 */
	public Foam( final Dimension minSize, final Dimension maxSize )
	{
		this( minSize, maxSize, 0.5f );
	}

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param maxSize
	 *            maximum size to use
	 * @param bias
	 *            calculate preferred size as a proportion between min and max sizes
	 */
	public Foam( final Dimension maxSize, final float bias )
	{
		this( new Dimension( 0, 0 ), maxSize, bias );
	}

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param maxSize
	 *            maximum size to use
	 */
	public Foam( final Dimension maxSize )
	{
		this( maxSize, 0.5f );
	}

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param width
	 *            max width to use
	 * @param height
	 *            max height to use
	 * @param bias
	 *            calculate preferred size as a proportion between min and max sizes
	 */
	public Foam( final int width, final int height, final float bias )
	{
		this( new Dimension( 0, 0 ), new Dimension( Math.round( width * bias ), Math.round( height * bias ) ),
				new Dimension( width, height ) );
	}

	/**
	 * Create a space filler with passed set of sizes.
	 *
	 * @param width
	 *            max width to use
	 * @param height
	 *            max height to use
	 */
	public Foam( final int width, final int height )
	{
		this( width, height, 0.5f );
	}

	@Override
	public Dimension getPreferredSize()
	{
		return preferredSize;
	}

	@Override
	public Dimension getMinimumSize()
	{
		return minSize;
	}

	@Override
	public Dimension getMaximumSize()
	{
		return maxSize;
	}

	/**
	 * Create a new space filler based on the scaling factor applied to current sizes.
	 * 
	 * @param scale
	 *            scale to apply to current sizes
	 * @return new space filler
	 */
	public Foam scale( final float scale )
	{
		return new Foam( new Dimension( Math.round( minSize.width * scale ), Math.round( minSize.height * scale ) ),
				new Dimension( Math.round( preferredSize.width * scale ), Math.round( preferredSize.height * scale ) ),
				new Dimension( Math.round( maxSize.width * scale ), Math.round( maxSize.height * scale ) ) );
	}

	/**
	 * Create a new space filler based on current sizes, with a new bias.
	 * 
	 * @param bias
	 *            bias to apply to current sizes
	 * @return new space filler
	 */
	public Foam bias( final float bias )
	{
		return new Foam( minSize, maxSize, bias );
	}

}
