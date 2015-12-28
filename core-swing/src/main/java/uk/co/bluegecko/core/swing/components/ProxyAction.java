package uk.co.bluegecko.core.swing.components;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;


/**
 * Allow an action to act as a proxy.
 *
 */
public final class ProxyAction extends AbstractAction
{

	private static final long serialVersionUID = -1541906328869403530L;

	private Action proxy;

	/**
	 * Create the action that will pass to a proxy.
	 * 
	 * @param name
	 *            action name
	 */
	public ProxyAction( final String name )
	{
		super( name );

		setEnabled( false );
	}

	@Override
	public void actionPerformed( final ActionEvent e )
	{
		if ( proxy != null )
		{
			proxy.actionPerformed( e );
		}
	}

	protected Action getProxy()
	{
		return proxy;
	}

	protected void setProxy( final Action proxy )
	{
		this.proxy = proxy;

		if ( proxy != null )
		{
			setEnabled( true );
			putValue( SHORT_DESCRIPTION, proxy.getValue( SHORT_DESCRIPTION ) );
			putValue( LONG_DESCRIPTION, proxy.getValue( LONG_DESCRIPTION ) );
			putValue( SMALL_ICON, proxy.getValue( SMALL_ICON ) );
			putValue( LARGE_ICON_KEY, proxy.getValue( LARGE_ICON_KEY ) );
		}
		else
		{
			setEnabled( false );
		}
	}

}