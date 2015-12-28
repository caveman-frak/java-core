package uk.co.bluegecko.core.swing.table;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


/**
 * Abstract implementation of a {@link TableDefinition} and {@link DataSource}
 *
 * @param <T>
 *            the data type
 */
public abstract class DefaultTableDefinition< T > extends AbstractTableModel
		implements TableDefinition< T >, DataSource< T >
{

	private static final long serialVersionUID = 6375703998454773376L;

	private final DataDefinition< T > dataDefinition;
	private final DataSource< T > dataSource;
	private final List< Integer > columnMap;
	private final Map< String, ColumnAttributes > columnAttributeMap;
	private final TableColumnModel columnModel;
	private boolean editable;

	/**
	 * Create a table definition.
	 * 
	 * @param dataDefinition
	 *            the data definition
	 * @param dataSource
	 *            the data source
	 * @param columnAttributes
	 *            the column attributes
	 */
	public DefaultTableDefinition( final DataDefinition< T > dataDefinition, final DataSource< T > dataSource,
			final ColumnAttributes... columnAttributes )
	{
		super();

		this.dataDefinition = dataDefinition;
		this.dataSource = dataSource;
		this.columnMap = new ArrayList< >();
		this.columnAttributeMap = createColumnAttributeMap( columnAttributes );
		this.columnModel = createColumnModel( columnAttributes );
	}

	private Map< String, ColumnAttributes > createColumnAttributeMap( final ColumnAttributes[] columnAttributes )
	{
		final Map< String, ColumnAttributes > results = new HashMap< >();
		for ( final ColumnAttributes attributes : columnAttributes )
		{
			results.put( attributes.getIdentifier(), attributes );
		}
		return results;
	}

	protected TableColumnModel createColumnModel( final ColumnAttributes[] columnAttributes )
	{
		final TableColumnModel columnModel = new DefaultTableColumnModel();

		for ( final ColumnAttributes attributes : columnAttributes )
		{
			final String identifier = attributes.getIdentifier();
			final int columnIndex = dataDefinition.getColumnIndex( identifier );
			if ( columnIndex > -1 )
			{
				columnModel.addColumn( createColumn( columnIndex, attributes ) );
				columnMap.add( columnIndex );
			}
		}
		return columnModel;
	}

	private TableColumn createColumn( final int modelIndex, final ColumnAttributes attributes )
	{
		final TableColumn tableColumn = new TableColumn( modelIndex );
		tableColumn.setIdentifier( attributes.getIdentifier() );
		tableColumn.setHeaderValue( attributes.getHeader() );
		tableColumn.setHeaderRenderer( attributes.getHeaderRenderer() );
		tableColumn.setCellRenderer( attributes.getCellRenderer() );
		tableColumn.setCellEditor( attributes.getCellEditor() );
		tableColumn.setMaxWidth( attributes.getMaxWidth() );
		tableColumn.setPreferredWidth( attributes.getPreferredWidth() );
		tableColumn.setMinWidth( attributes.getMinWidth() );
		tableColumn.setWidth( attributes.getWidth() );
		tableColumn.setResizable( attributes.isResizable() );

		return tableColumn;
	}

	@Override
	public TableColumnModel getColumnModel()
	{
		return columnModel;
	}

	@Override
	public boolean isEditable()
	{
		return editable;
	}

	/**
	 * Set the table as editable.
	 *
	 * @param editable
	 *            is editable
	 */
	public void setEditable( final boolean editable )
	{
		this.editable = editable;
	}

	@Override
	public int getRowCount()
	{
		return dataSource.getRowCount();
	}

	@Override
	public T getRow( final int rowIndex )
	{
		return dataSource.getRow( rowIndex );
	}

	@Override
	public int getColumnCount()
	{
		return dataDefinition.getColumnCount();
	}

	@Override
	public String getColumnName( final int columnIndex )
	{
		return columnModel.getColumn( columnIndex ).getHeaderValue().toString();
	}

	@Override
	public Object getValueAt( final int rowIndex, final int columnIndex )
	{
		return dataDefinition.getValueAt( dataSource, rowIndex, columnIndex );
	}

	@Override
	public void setValueAt( final Object value, final int rowIndex, final int columnIndex )
	{
		dataDefinition.setValueAt( dataSource, rowIndex, columnIndex, value );
	}

	@Override
	public boolean isCellEditable( final int rowIndex, final int columnIndex )
	{
		return editable && columnAttributeMap.get( dataDefinition.getColumnIdentifier( columnIndex ) ).isEditable()
				&& dataDefinition.isEditable( dataSource, rowIndex, columnIndex );
	}

	@Override
	public Class< ? > getColumnClass( final int columnIndex )
	{
		return dataDefinition.getColumnClass( columnIndex );
	}

	@Override
	public Comparator< ? > getColumnComparator( final int columnIndex )
	{
		return dataDefinition.getColumnComparator( columnIndex );
	}

	@Override
	public int findColumn( final String columnName )
	{
		return dataDefinition.getColumnIndex( columnName );
	}

	@Override
	public void applyTo( final JTable table )
	{
		table.setAutoCreateColumnsFromModel( false );
		table.setModel( this );
		table.setColumnModel( getColumnModel() );
	}

}
