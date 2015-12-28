package uk.co.bluegecko.core.swing.table;


import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 * Implementation of a table header supporting column definitions and sorting headers.
 *
 * @param <T>
 */
public class TableHeader< T > extends JTableHeader
{

	private static final long serialVersionUID = 4140296629297838686L;

	private final RowSorter< TableDefinition< T > > rowSorter;

	/**
	 * Create a table header.
	 * 
	 * @param columnModel
	 *            the column model
	 * @param rowSorter
	 *            the row sorter
	 */
	public TableHeader( final TableColumnModel columnModel, final RowSorter< TableDefinition< T > > rowSorter )
	{
		super( columnModel );

		this.rowSorter = rowSorter;

		addRowSorterListener();
	}

	private void addRowSorterListener()
	{
		rowSorter.addRowSorterListener( e ->
			{
				if ( e.getType() == RowSorterEvent.Type.SORT_ORDER_CHANGED )
				{
					invalidate();
				}
			} );
	}

	@Override
	protected TableCellRenderer createDefaultRenderer()
	{
		final DefaultTableCellRenderer label = new HeaderRenderer();
		label.setHorizontalAlignment( SwingConstants.CENTER );
		return label;
	}

}
