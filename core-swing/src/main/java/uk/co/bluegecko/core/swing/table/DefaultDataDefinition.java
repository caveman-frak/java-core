package uk.co.bluegecko.core.swing.table;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


/**
 * Concrete implementation of a {@link DataDefinition}.
 *
 * @param <T>
 *            the data type
 */
public class DefaultDataDefinition< T > implements DataDefinition< T >
{

	private final DataAdapter< T > adapter;
	private final ColumnDefinition< ? >[] columns;
	private final Map< String, Integer > identifierIndex;

	/**
	 * Create a data definition.
	 * 
	 * @param adapter
	 *            the data adapter
	 * @param columns
	 *            the column definitions
	 */
	public DefaultDataDefinition( final DataAdapter< T > adapter, final ColumnDefinition< ? >... columns )
	{
		super();

		this.adapter = adapter;
		this.columns = columns;
		this.identifierIndex = createIdentifierIndex();
	}

	@Override
	public int getColumnCount()
	{
		return Math.min( adapter.getColumnCount(), columns.length );
	}

	@Override
	public String getColumnIdentifier( final int columnIndex )
	{
		return columns[columnIndex].getIdentifier();
	}

	@Override
	public int getColumnIndex( final String identifier )
	{
		return identifierIndex.containsKey( identifier ) ? identifierIndex.get( identifier ) : -1;
	}

	@Override
	public Class< ? > getColumnClass( final int columnIndex )
	{
		return columns[columnIndex].getColumnClass();
	}

	@Override
	public Comparator< ? > getColumnComparator( final int columnIndex )
	{
		return columns[columnIndex].getComparator();
	}

	@Override
	public Object getValueAt( final DataSource< T > source, final int rowIndex, final int columnIndex )
	{
		return adapter.getValueAt( source.getRow( rowIndex ), columnIndex );
	}

	@Override
	public void setValueAt( final DataSource< T > source, final int rowIndex, final int column, final Object value )
	{
		adapter.setValueAt( source.getRow( rowIndex ), column, value );
	}

	@Override
	public boolean isEditable( final DataSource< T > source, final int rowIndex, final int columnIndex )
	{
		return columns[columnIndex].isEditable() && adapter.isEditable( source.getRow( rowIndex ), columnIndex );
	}

	private Map< String, Integer > createIdentifierIndex()
	{
		final Map< String, Integer > result = new HashMap< >();
		for ( int i = 0; i < columns.length; i++ )
		{
			result.put( columns[i].getIdentifier(), i );
		}
		return result;
	}

}
