package uk.co.bluegecko.core.swing.table.line;


import uk.co.bluegecko.core.swing.table.DataAdapter;


/**
 * A line of data.
 *
 * @param <T>
 *            type of data
 */
public class LineData< T > implements Line< T >
{

	private final T data;

	/**
	 * Create a line of data.
	 * 
	 * @param data
	 *            the data
	 */
	public LineData( final T data )
	{
		super();

		this.data = data;
	}

	@Override
	public Object getValueAt( final DataAdapter< T > adapter, final int columnIndex )
	{
		return adapter.getValueAt( data, columnIndex );
	}

	@Override
	public void setValueAt( final DataAdapter< T > adapter, final int columnIndex, final Object value )
	{
		adapter.setValueAt( data, columnIndex, value );
	}

	@Override
	public boolean isEditable( final DataAdapter< T > adapter, final int columnIndex )
	{
		return adapter.isEditable( data, columnIndex );
	}

}
