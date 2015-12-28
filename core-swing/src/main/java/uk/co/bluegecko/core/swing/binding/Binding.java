package uk.co.bluegecko.core.swing.binding;


import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JLabel;


/**
 * Create a binding for a control and its corresponding label.
 *
 * @param <T>
 *            the type of control
 */
public class Binding< T extends Component > implements Bindable, PropertyChangeListener
{

	private final PropertyChangeSupport support;
	private final String name;
	private final T component;
	private JLabel label;
	private ControlState controlState;
	private DataState dataState;

	/**
	 * Construct a component binding.
	 *
	 * @param name
	 *            binding / field name
	 * @param component
	 *            bound component
	 */
	public Binding( final String name, final T component )
	{
		super();

		this.support = new PropertyChangeSupport( this );
		this.name = name;
		this.component = component;
		this.controlState = ControlState.READ_WRITE;
		this.dataState = DataState.UNKNOWN;

		bind();
	}

	protected void bind()
	{
		component.addPropertyChangeListener( this );
	}

	/**
	 * Configure the bound component on a property change.
	 * 
	 * @param e
	 *            property change event
	 */
	protected void configureComponent( final PropertyChangeEvent e )
	{
		component.setEnabled( controlState != ControlState.DISABLED );
		component.setVisible( controlState != ControlState.HIDDEN );
	}

	/**
	 * Configure the bound label on a property change.
	 * 
	 * @param e
	 *            property change event
	 */
	protected void configureLabel( final PropertyChangeEvent e )
	{
		label.setEnabled( controlState != ControlState.DISABLED );
		label.setVisible( controlState != ControlState.HIDDEN );
	}

	/**
	 * Get the bound component.
	 *
	 * @return bound component
	 */
	public T getComponent()
	{
		return component;
	}

	/**
	 * Get the associated label.
	 *
	 * @return associated label
	 */
	public JLabel getLabel()
	{
		return label;
	}

	/**
	 * Get the control state for the component.
	 *
	 * @return current control state
	 */
	public ControlState getControlState()
	{
		return controlState;
	}

	/**
	 * Set the control state for the component.
	 *
	 * @param newState
	 *            updated control state
	 */
	public void setControlState( final ControlState newState )
	{
		if ( newState != controlState )
		{
			final ControlState oldState = controlState;
			this.controlState = newState;

			support.firePropertyChange( "ControlState", oldState, newState );
		}
	}

	/**
	 * Get the data state for the component.
	 *
	 * @return current data state
	 */
	public DataState getDataState()
	{
		return dataState;
	}

	/**
	 * Set the data state for the component.
	 *
	 * @param newState
	 *            updated data state
	 */
	public void setDataState( final DataState newState )
	{
		if ( newState != dataState )
		{
			final DataState oldState = dataState;
			this.dataState = newState;

			support.firePropertyChange( "DataState", oldState, newState );
		}
	}

	/**
	 * Get the binding name.
	 *
	 * @return binding name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Create a label for the component.
	 *
	 * @return a label
	 */
	public JLabel createLabel()
	{
		if ( label == null )
		{
			final JLabel label = new JLabel();
			label.setName( name );
			label.setLabelFor( component );
		}

		return label;
	}

	@Override
	public void propertyChange( final PropertyChangeEvent e )
	{
		configureLabel( e );
		configureComponent( e );
	}

	@Override
	public void bind( final Object target )
	{}

}
