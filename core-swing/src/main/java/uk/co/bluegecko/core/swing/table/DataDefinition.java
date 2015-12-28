package uk.co.bluegecko.core.swing.table;


import java.util.Comparator;


/**
 * Data definition for use in tables.
 *
 * @param <T>
 *            type of data
 */
public interface DataDefinition< T >
{

	/**
	 * Number of columns.
	 *
	 * @return column count
	 */
	public int getColumnCount();

	/**
	 * Identifier for a column.
	 *
	 * @param columnIndex
	 *            the column
	 * @return the identifier
	 */
	public String getColumnIdentifier( int columnIndex );

	/**
	 * Index for a column.
	 *
	 * @param identifier
	 *            column identifier
	 * @return column index
	 */
	public int getColumnIndex( String identifier );

	/**
	 * Class for a column.
	 *
	 * @param columnIndex
	 *            the column
	 * @return the type
	 */
	public Class< ? > getColumnClass( int columnIndex );

	/**
	 * Value for a column and row.
	 *
	 * @param source
	 *            the data source
	 * @param rowIndex
	 *            the row
	 * @param columnIndex
	 *            the column
	 * @return the cell value
	 */
	public Object getValueAt( DataSource< T > source, int rowIndex, int columnIndex );

	/**
	 * Set the value for a column and row.
	 *
	 * @param source
	 *            the data source
	 * @param rowIndex
	 *            the row
	 * @param columnIndex
	 *            the column
	 * @param value
	 *            the cell value
	 */
	public void setValueAt( DataSource< T > source, int rowIndex, int columnIndex, Object value );

	/**
	 * Is a value for a column and row editable.
	 *
	 * @param source
	 *            the data source
	 * @param rowIndex
	 *            the row
	 * @param columnIndex
	 *            the column
	 *
	 * @return is the cell editable
	 */
	public boolean isEditable( DataSource< T > source, int rowIndex, int columnIndex );

	/**
	 * The data comparator for sorting the column.
	 * 
	 * @param columnIndex
	 *            the column
	 * @return the comparator
	 */
	public Comparator< ? > getColumnComparator( int columnIndex );

}
