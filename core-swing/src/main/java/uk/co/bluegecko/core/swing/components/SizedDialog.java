package uk.co.bluegecko.core.swing.components;


import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;


/**
 * A Dialog that supports setting a max and/or minimum size.
 * If either dimension is set to {@link uk.co.bluegecko.core.swing.components.SizedHelper#USE_PREFERRED_SIZE} then that
 * dimension will be locked to the preferred size.
 * If either dimension is set to {@link uk.co.bluegecko.core.swing.components.SizedHelper#UNRESTRICTED_SIZE} then that
 * dimension will be unconstrained.
 */
public class SizedDialog extends JDialog implements Sized
{

	private static final long serialVersionUID = -2086433905745876376L;

	/** The SizeHelper for this Dialog. */
	private SizedHelper sizeHelper;

	/**
	 * Construct a dialog owned by a dialog.
	 *
	 * @param owner
	 *            the non-null Dialog from which the dialog is displayed
	 * @param title
	 *            the String to display in the dialog's title bar
	 * @param modal
	 *            true for a modal dialog, false for one that allows other windows to be active at the same time
	 */
	public SizedDialog( final Dialog owner, final String title, final boolean modal )
	{
		super( owner, title, modal );

		initSizedDialog();
	}

	/**
	 * Construct a dialog owned by a frame.
	 *
	 * @param owner
	 *            the non-null Frame from which the dialog is displayed
	 * @param title
	 *            the String to display in the dialog's title bar
	 * @param modal
	 *            true for a modal dialog, false for one that allows other windows to be active at the same time
	 */
	public SizedDialog( final Frame owner, final String title, final boolean modal )
	{
		super( owner, title, modal );

		initSizedDialog();
	}

	/** Initialise the dialog. */
	private final void initSizedDialog()
	{
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
		dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );
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
