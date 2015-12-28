package uk.co.bluegecko.core.swing.table.line;


import uk.co.bluegecko.core.swing.table.DataAdapter;


/**
 * A line of data.
 *
 * @param <T>
 *            type of data
 */
public interface Line< T >
{

	/**
	 * Is the cell editable?
	 *
	 * @param adapter
	 *            data adapter to use
	 * @param columnIndex
	 *            column to use
	 * @return is editable
	 */
	public abstract boolean isEditable( DataAdapter< T > adapter, int columnIndex );

	/**
	 * Set the value of a cell.
	 * 
	 * @param adapter
	 *            data adapter to use
	 * @param columnIndex
	 *            column to use
	 * @param value
	 *            new cell value
	 */
	public abstract void setValueAt( DataAdapter< T > adapter, int columnIndex, Object value );

	/**
	 * Get the value of a cell.
	 * 
	 * @param adapter
	 *            data adapter to use
	 * @param columnIndex
	 *            column to use
	 * @return the cell value
	 */
	public abstract Object getValueAt( DataAdapter< T > adapter, int columnIndex );

}
