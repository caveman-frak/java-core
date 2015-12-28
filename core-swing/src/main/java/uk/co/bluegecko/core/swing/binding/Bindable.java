package uk.co.bluegecko.core.swing.binding;


/**
 * Mark a component as being available for binding.
 *
 */
public interface Bindable
{

	/**
	 * Bind the target component.
	 * 
	 * @param target
	 *            component
	 */
	public void bind( Object target );

}
