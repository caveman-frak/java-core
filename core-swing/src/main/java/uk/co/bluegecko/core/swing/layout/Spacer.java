package uk.co.bluegecko.core.swing.layout;


import java.awt.Dimension;

import javax.swing.JPanel;


/**
 * Fixed size space filler.
 */
public class Spacer extends JPanel
{

	private static final long serialVersionUID = 3791529173439691858L;

	private final Dimension size;

	/**
	 * Create a fixed size space filler.
	 * 
	 * @param size
	 *            size of filler
	 */
	public Spacer( final Dimension size )
	{
		super();

		this.size = size;
	}

	/**
	 * Create a fixed size space filler.
	 * 
	 * @param width
	 *            width of filler
	 * @param height
	 *            height of filler
	 */
	public Spacer( final int width, final int height )
	{
		this( new Dimension( width, height ) );
	}

	/**
	 * Create a fixed zero size space filler
	 */
	public Spacer()
	{
		this( new Dimension( 0, 0 ) );
	}

	@Override
	public Dimension getPreferredSize()
	{
		return size;
	}

	@Override
	public Dimension getMinimumSize()
	{
		return size;
	}

	@Override
	public Dimension getMaximumSize()
	{
		return size;
	}

	/**
	 * Create a new space filler scaled from the existing size.
	 * 
	 * @param scale
	 *            scaling factor
	 * @return new filler
	 */
	public Spacer scale( final float scale )
	{
		return new Spacer( Math.round( size.width * scale ), Math.round( size.height * scale ) );
	}

}
