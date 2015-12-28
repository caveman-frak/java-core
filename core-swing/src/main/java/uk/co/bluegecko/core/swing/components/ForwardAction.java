package uk.co.bluegecko.core.swing.components;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;


/**
 * Allows an action to be forwarded to another implementation.
 *
 */
public final class ForwardAction extends AbstractAction
{

	private static final long serialVersionUID = -1541906328869403530L;

	private final Action forward;

	/**
	 * Create a wrapper action that will forward to the supplied action.
	 * 
	 * @param name
	 *            action name
	 * @param forward
	 *            forwarded target
	 */
	public ForwardAction( final String name, final Action forward )
	{
		super( name );

		this.forward = forward;
	}

	@Override
	public void actionPerformed( final ActionEvent e )
	{
		forward.actionPerformed( e );
	}

}