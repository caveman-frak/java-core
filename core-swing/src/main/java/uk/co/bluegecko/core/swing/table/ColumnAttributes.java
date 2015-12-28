package uk.co.bluegecko.core.swing.table;


import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


/**
 * Attributes for a table column.
 *
 */
public interface ColumnAttributes
{

	/**
	 * Get the identifier.
	 * 
	 * @return the identifier
	 */
	public String getIdentifier();

	/**
	 * Get the heading value
	 * 
	 * @return the heading
	 */
	public Object getHeader();

	/**
	 * Get the heading renderer.
	 * 
	 * @return the heading renderer
	 */
	public TableCellRenderer getHeaderRenderer();

	/**
	 * Get the cell renderer.
	 * 
	 * @return the cell renderer
	 */
	public TableCellRenderer getCellRenderer();

	/**
	 * Get the cell editor.
	 * 
	 * @return the cell editor
	 */
	public TableCellEditor getCellEditor();

	/**
	 * Get the minimum width.
	 * 
	 * @return the minimum width
	 */
	public int getMinWidth();

	/**
	 * Get the preferred width.
	 * 
	 * @return the preferred width
	 */
	public int getPreferredWidth();

	/**
	 * Get the maximum width.
	 * 
	 * @return the maximum width
	 */
	public int getMaxWidth();

	/**
	 * Get if the column is re-sizable.
	 * 
	 * @return is re-sizable
	 */
	public boolean isResizable();

	/**
	 * Get if the column is editable.
	 * 
	 * @return is editable
	 */
	public boolean isEditable();

	/**
	 * Get the width.
	 * 
	 * @return the width
	 */
	public int getWidth();

	/**
	 * Set the width.
	 * 
	 * @param width
	 *            the width
	 */
	public void setWidth( int width );

	/**
	 * Get the column visibility.
	 * 
	 * @return the visibility
	 */
	public boolean isVisible();

	/**
	 * Set the column visibility.
	 * 
	 * @param visible
	 *            the visibility
	 */
	public void setVisible( boolean visible );

}
