package uk.co.bluegecko.core.swing.layout;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * A panel designed to hold a set of buttons.
 *
 */
public class ButtonPanel extends JPanel
{

	private static final long serialVersionUID = 335088695866047881L;

	private final JPanel leading;
	private final JPanel trailing;
	private final Link.Manager manager;

	/**
	 * Construct a new button panel.
	 */
	public ButtonPanel()
	{
		super( new BorderLayout() );

		manager = new Link.Manager( Direction.HORIZONTAL );

		add( leading = new JPanel( new FlowLayout( FlowLayout.LEADING ) ), BorderLayout.LINE_START );
		add( trailing = new JPanel( new FlowLayout( FlowLayout.TRAILING ) ), BorderLayout.LINE_END );
	}

	/**
	 * Add a leading button.
	 * 
	 * @param button
	 *            button to add
	 */
	public void addLeading( final JButton button )
	{
		leading.add( manager.link( button ) );
	}

	/**
	 * Add a leading button.
	 * 
	 * @param action
	 *            button to add
	 */
	public void addLeading( final Action action )
	{
		addLeading( new JButton( action ) );
	}

	/**
	 * Add leading buttons.
	 * 
	 * @param actions
	 *            buttons to add
	 */
	public void addLeading( final Action... actions )
	{
		for ( final Action action : actions )
		{
			addLeading( new JButton( action ) );
		}
	}

	/**
	 * Add a trailing button.
	 * 
	 * @param button
	 *            button to add
	 */
	public void addTrailing( final JButton button )
	{
		trailing.add( manager.link( button ) );
	}

	/**
	 * Add a trailing button.
	 * 
	 * @param action
	 *            button to add
	 */
	public void addTrailing( final Action action )
	{
		addTrailing( new JButton( action ) );
	}

	/**
	 * Add a trailing button.
	 * 
	 * @param actions
	 *            buttons to add
	 */
	public void addTrailing( final Action... actions )
	{
		for ( final Action action : actions )
		{
			addTrailing( new JButton( action ) );
		}
	}

	@Override
	public Component add( final Component component )
	{
		trailing.add( manager.link( component ) );
		return component;
	}

	/**
	 * Set the default button by index. Use index of -1 to clear default button.
	 * 
	 * @param index
	 *            index of default button
	 *
	 */
	public void setDefaultButtonIndex( final int index )
	{
		if ( index > -1 )
		{
			Component c = null;
			if ( index > leading.getComponentCount() )
			{
				c = trailing.getComponent( index - leading.getComponentCount() );
			}
			else
			{
				c = leading.getComponent( index );
			}
			if ( c != null )
			{
				if ( ( ( Link ) c ).getComponent() instanceof JButton )
				{
					getRootPane().setDefaultButton( ( JButton ) ( ( Link ) c ).getComponent() );
				}
			}
		}
	}

}
