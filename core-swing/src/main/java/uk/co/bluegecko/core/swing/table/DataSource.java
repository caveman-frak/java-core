package uk.co.bluegecko.core.swing.table;


/**
 * Source for data objects.
 *
 * @param <T>
 *            type of data
 */
public interface DataSource< T >
{

	/**
	 * Count of available rows.
	 * 
	 * @return row count
	 */
	public int getRowCount();

	/**
	 * Get a specific row.
	 * 
	 * @param rowIndex
	 *            row to get
	 * @return data for row
	 */
	public T getRow( int rowIndex );

}
