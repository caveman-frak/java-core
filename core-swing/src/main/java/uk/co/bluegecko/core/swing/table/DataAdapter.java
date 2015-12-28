package uk.co.bluegecko.core.swing.table;


/**
 * Adapter for displaying data in a table.
 *
 * @param <T>
 *            type of data
 */
public interface DataAdapter< T >
{

	/**
	 * Get the number of columns.
	 * 
	 * @return column count
	 */
	public int getColumnCount();

	/**
	 * Get the value for a cell.
	 * 
	 * @param row
	 *            the row of data
	 * @param columnIndex
	 *            the column
	 * @return the value
	 */
	public Object getValueAt( T row, int columnIndex );

	/**
	 * Set the value for a cell.
	 * 
	 * @param row
	 *            the row of data
	 * @param columnIndex
	 *            the column
	 * @param value
	 *            the value
	 */
	public void setValueAt( T row, int columnIndex, Object value );

	/**
	 * Get if the column is editable.
	 * 
	 * @param row
	 *            the row of data
	 * @param columnIndex
	 *            the column
	 *
	 * @return is editable
	 */
	public boolean isEditable( T row, int columnIndex );

}
