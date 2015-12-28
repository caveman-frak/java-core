package uk.co.bluegecko.core.swing.binding;


import java.util.HashSet;
import java.util.Set;


/**
 * Group connected bindable components.
 */
public class BindingGroup implements Bindable
{

	private final Set< Bindable > bindings;

	/**
	 * Create a new binding group.
	 */
	public BindingGroup()
	{
		super();

		bindings = new HashSet< >();
	}

	@Override
	public void bind( final Object target )
	{
		for ( final Bindable bindable : bindings )
		{
			bindable.bind( target );
		}
	}

	/**
	 * Add a new component into the group.
	 * 
	 * @param bindable
	 *            new component for group
	 */
	public void add( final Bindable bindable )
	{
		bindings.add( bindable );
	}

	/**
	 * Remove a component from the group.
	 * 
	 * @param bindable
	 *            component to remove from group
	 */
	public void remove( final Bindable bindable )
	{
		bindings.remove( bindable );
	}

}
