package uk.co.bluegecko.core.swing.table;


/**
 * Abstract version of {@link DataAdapter} for immutable data.
 *
 * @param <T>
 *            the data type
 */
public abstract class ImmutableDataAdapter< T > implements DataAdapter< T >
{

	protected ImmutableDataAdapter()
	{}

	@Override
	public final void setValueAt( final T row, final int columnIndex, final Object value )
	{}

	@Override
	public final boolean isEditable( final T row, final int columnIndex )
	{
		return false;
	}

}
