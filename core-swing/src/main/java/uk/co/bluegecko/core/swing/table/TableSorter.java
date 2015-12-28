package uk.co.bluegecko.core.swing.table;


import java.util.Comparator;

import javax.swing.table.TableRowSorter;

import uk.co.bluegecko.core.util.collate.DontSort;


/**
 * Table sorter, allows sorting by column headers.
 *
 * @param <T>
 *            data type
 */
public class TableSorter< T > extends TableRowSorter< TableDefinition< T > >
{

	/**
	 * Create a new table sorter.
	 * 
	 * @param model
	 *            the table definition
	 * @param maxKeys
	 *            the maximum number of sorted columns
	 */
	public TableSorter( final TableDefinition< T > model, final int maxKeys )
	{
		super( model );

		setMaxSortKeys( maxKeys );
	}

	@Override
	public Comparator< ? > getComparator( final int columnIndex )
	{
		if ( getModel().getColumnComparator( columnIndex ) != null )
		{
			return getModel().getColumnComparator( columnIndex );
		}
		return super.getComparator( columnIndex );
	}

	@Override
	public boolean isSortable( final int columnIndex )
	{
		return getModel().getColumnComparator( columnIndex ) != DontSort.DONT_SORT;
	}

}
