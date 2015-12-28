package uk.co.bluegecko.core.swing.table.line;


import uk.co.bluegecko.core.swing.table.DataAdapter;


/**
 * Representation adapter of a line of data.
 *
 * @param <T>
 *            type of the data
 */
public class LineAdapter< T > implements DataAdapter< Line< T > >
{

	private final DataAdapter< T > adapter;

	/**
	 * Create a line representation using the supplied data adapter
	 *
	 * @param adapter
	 *            data adapter to use
	 */
	public LineAdapter( final DataAdapter< T > adapter )
	{
		super();

		this.adapter = adapter;
	}

	@Override
	public int getColumnCount()
	{
		return adapter.getColumnCount();
	}

	@Override
	public Object getValueAt( final Line< T > row, final int columnIndex )
	{
		return row.getValueAt( adapter, columnIndex );
	}

	@Override
	public void setValueAt( final Line< T > row, final int columnIndex, final Object value )
	{
		row.setValueAt( adapter, columnIndex, value );
	}

	@Override
	public boolean isEditable( final Line< T > row, final int columnIndex )
	{
		return row.isEditable( adapter, columnIndex );
	}

}
