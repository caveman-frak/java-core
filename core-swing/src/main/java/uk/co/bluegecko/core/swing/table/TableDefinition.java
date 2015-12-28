package uk.co.bluegecko.core.swing.table;


import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


/**
 * Definition of a table model.
 *
 * @param <T>
 *            the data type
 */
public interface TableDefinition< T > extends TableModel
{

	/**
	 * Is the table editable.
	 * 
	 * @return is editable
	 */
	public boolean isEditable();

	/**
	 * Get the column model for the table
	 * 
	 * @return the column model
	 */
	public TableColumnModel getColumnModel();

	/**
	 * Apply this model to a table.
	 * 
	 * @param table
	 *            the table
	 */
	public void applyTo( JTable table );

	/**
	 * Get the comparator for a column.
	 * 
	 * @param columnIndex
	 *            the column
	 * @return the comparator
	 */
	public Comparator< ? > getColumnComparator( int columnIndex );

}
