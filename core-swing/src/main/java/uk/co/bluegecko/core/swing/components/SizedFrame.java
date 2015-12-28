package uk.co.bluegecko.core.swing.components;


import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * A Frame that supports setting a max and/or minimum size.
 * If either dimension is set to {@link uk.co.bluegecko.core.swing.components.SizedHelper#USE_PREFERRED_SIZE} then that
 * dimension will be locked to the preferred size.
 * If either dimension is set to {@link uk.co.bluegecko.core.swing.components.SizedHelper#UNRESTRICTED_SIZE} then that
 * dimension will be unconstrained.
 */
public class SizedFrame extends JFrame implements Sized
{

	private static final long serialVersionUID = -6716238329998410729L;

	/** The SizeHelper for this Frame. */
	private SizedHelper sizeHelper;

	/** Construct a default SizedFrame. */
	public SizedFrame()
	{
		super();

		initSizedFrame();
	}

	/**
	 * Construct a SizedFrame.
	 *
	 * @param title
	 *            the title for the frame
	 */
	public SizedFrame( final String title )
	{
		super( title );

		initSizedFrame();
	}

	/**
	 * Construct a SizedFrame.
	 *
	 * @param gc
	 *            the GraphicsConfiguration for the frame
	 */
	public SizedFrame( final GraphicsConfiguration gc )
	{
		super( gc );

		initSizedFrame();
	}

	/**
	 * Construct a SizedFrame.
	 *
	 * @param title
	 *            the title for the frame
	 * @param gc
	 *            the GraphicsConfiguration for the frame
	 */
	public SizedFrame( final String title, final GraphicsConfiguration gc )
	{
		super( title, gc );

		initSizedFrame();
	}

	/** Initialise the frame. */
	private final void initSizedFrame()
	{
		setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
		sizeHelper = new SizedHelper( this );

		addWindowListener( new WindowAdapter()
		{

			@Override
			public void windowClosing( final WindowEvent e )
			{
				closing( e );
			}

			@Override
			public void windowClosed( final WindowEvent e )
			{
				closing( e );
			}
		} );
	}

	/**
	 * Set the minimum size. Notify the SizeHelper to ensure the window stays within correct bounds.
	 *
	 * @param minimumSize
	 *            the new minimum size
	 */
	@Override
	public final void setMinimumSize( final Dimension minimumSize )
	{
		super.setMinimumSize( minimumSize );
		sizeHelper.notifySizeChanged();
	}

	/**
	 * Set the maximum size. Notify the SizeHelper to ensure the window stays within correct bounds.
	 *
	 * @param maximumSize
	 *            the new maximum size
	 */
	@Override
	public final void setMaximumSize( final Dimension maximumSize )
	{
		super.setMaximumSize( maximumSize );
		sizeHelper.notifySizeChanged();
	}

	/** Close the frame. Dispatches a WINDOW_CLOSING event. */
	public final void close()
	{
		final WindowEvent event = new WindowEvent( this, WindowEvent.WINDOW_CLOSING );
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( event );
	}

	/**
	 * Called when the Frame is closing, either from a WINDOW_CLOSED or WINDOW_CLOSING event.
	 *
	 * @param e
	 *            the event that caused the close
	 */
	protected void closing( final WindowEvent e )
	{
		/* do nothing */
	}

}
