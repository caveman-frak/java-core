package uk.co.bluegecko.core.swing.table.rendering;


import java.util.Collection;

import uk.co.bluegecko.core.swing.table.Table;


/**
 * Provider for rendering hints.
 */
public interface HintProvider
{

	/**
	 * Get the rendering hints for a header cell.
	 * 
	 * @param table
	 *            the table
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 * @param isSelected
	 *            is the cell selected
	 * @return collection of applicable rendering hints
	 */
	public Collection< RenderingHint< ? > > getHeaderHints( Table table, int rowIndex, int columnIndex,
			boolean isSelected );

	/**
	 * Get the rendering hints for a cell.
	 * 
	 * @param table
	 *            the table
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 * @param isSelected
	 *            is the cell selected
	 * @param isFocused
	 *            is the cell focused
	 * @return collection of applicable rendering hints
	 */
	public Collection< RenderingHint< ? > > getRenderingHints( Table table, int rowIndex, int columnIndex,
			boolean isSelected, boolean isFocused );

	/**
	 * Get the rendering hints for a cell while editing.
	 * 
	 * @param table
	 *            the table
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 * @param isSelected
	 *            is the cell selected
	 * @return collection of applicable rendering hints
	 */
	public Collection< RenderingHint< ? > > getEditingHints( Table table, int rowIndex, int columnIndex,
			boolean isSelected );

}
