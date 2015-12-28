package uk.co.bluegecko.core.swing.table;


import java.util.Comparator;


/**
 * Definition for a column.
 * 
 * @param <T>
 *            data type of column
 */
public interface ColumnDefinition< T >
{

	/**
	 * Get the identifier.
	 *
	 * @return the identifier
	 */
	public String getIdentifier();

	/**
	 * Get the class of column type
	 * 
	 * @return class
	 */
	public Class< T > getColumnClass();

	/**
	 * Get if the column is editable.
	 *
	 * @return is editable
	 */
	public boolean isEditable();

	/**
	 * Get the comparator.
	 *
	 * @return the comparator
	 */
	public Comparator< T > getComparator();

}
