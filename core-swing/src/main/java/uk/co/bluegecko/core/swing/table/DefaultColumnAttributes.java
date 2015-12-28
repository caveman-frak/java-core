package uk.co.bluegecko.core.swing.table;


import java.util.ResourceBundle;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import uk.co.bluegecko.core.util.resources.LocalisationHelper;
import uk.co.bluegecko.core.util.resources.ResourceHandler;


/**
 * Concrete implementation of a {@link ColumnAttributes}.
 *
 * @param <T>
 *            the data type
 */
public class DefaultColumnAttributes< T > implements ColumnAttributes
{

	private final ColumnDefinition< T > columnDefinition;
	private final Object header;
	private final TableCellRenderer headerRenderer;
	private final TableCellRenderer cellRenderer;
	private final TableCellEditor cellEditor;
	private final int minWidth;
	private final int preferredWidth;
	private final int maxWidth;
	private final boolean resizable;
	private final boolean editable;
	private int width;
	private boolean visible;

	/**
	 * Create column attributes.
	 *
	 * @param columnDefinition
	 *            the definition
	 * @param header
	 *            the header
	 * @param headerRenderer
	 *            the header renderer
	 * @param cellRenderer
	 *            the cell renderer
	 * @param cellEditor
	 *            the cell editor
	 * @param minWidth
	 *            the minimum width
	 * @param preferredWidth
	 *            the preferred width
	 * @param maxWidth
	 *            the maximum width
	 * @param resizable
	 *            is resizable
	 * @param editable
	 *            is editable
	 */
	public DefaultColumnAttributes( final ColumnDefinition< T > columnDefinition, final Object header,
			final TableCellRenderer headerRenderer, final TableCellRenderer cellRenderer,
			final TableCellEditor cellEditor, final int minWidth, final int preferredWidth, final int maxWidth,
			final boolean resizable, final boolean editable )
	{
		super();

		this.columnDefinition = columnDefinition;
		this.header = header;
		this.headerRenderer = headerRenderer;
		this.cellRenderer = cellRenderer;
		this.cellEditor = cellEditor;
		this.minWidth = minWidth;
		this.preferredWidth = Math.max( this.minWidth, preferredWidth );
		this.maxWidth = Math.max( this.preferredWidth, maxWidth );
		this.width = this.preferredWidth;
		this.resizable = resizable;
		this.editable = editable;
		this.visible = true;
	}

	@Override
	public String getIdentifier()
	{
		return columnDefinition.getIdentifier();
	}

	@Override
	public Object getHeader()
	{
		return header;
	}

	@Override
	public TableCellRenderer getHeaderRenderer()
	{
		return headerRenderer;
	}

	@Override
	public TableCellRenderer getCellRenderer()
	{
		return cellRenderer;
	}

	@Override
	public TableCellEditor getCellEditor()
	{
		return cellEditor;
	}

	@Override
	public int getMinWidth()
	{
		return minWidth;
	}

	@Override
	public int getPreferredWidth()
	{
		return preferredWidth;
	}

	@Override
	public int getMaxWidth()
	{
		return maxWidth;
	}

	@Override
	public boolean isResizable()
	{
		return resizable;
	}

	@Override
	public boolean isEditable()
	{
		return columnDefinition.isEditable() && editable;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public void setWidth( final int width )
	{
		if ( resizable )
		{
			this.width = Math.max( minWidth, Math.min( maxWidth, width ) );
		}
	}

	@Override
	public boolean isVisible()
	{
		return visible;
	}

	@Override
	public void setVisible( final boolean visible )
	{
		this.visible = visible;
	}

	/**
	 * Create column attributes.
	 *
	 * @param <T>
	 *            the column type
	 * @param columnDefinition
	 *            the definition
	 * @param header
	 *            the header
	 * @param width
	 *            the column width
	 * @param resizable
	 *            is resizable
	 * @return the column attributes
	 */
	public static final < T > ColumnAttributes column( final ColumnDefinition< T > columnDefinition,
			final Object header, final int width, final boolean resizable )
	{
		return new DefaultColumnAttributes< >( columnDefinition, header, null, null, null, 0, width, Short.MAX_VALUE,
				resizable, false );
	}

	/**
	 * Create column attributes.
	 *
	 * @param <T>
	 *            the column type
	 * @param columnDefinition
	 *            the definition
	 * @param header
	 *            the header
	 * @return the column attributes
	 */
	public static final < T > ColumnAttributes column( final ColumnDefinition< T > columnDefinition,
			final Object header )
	{
		return column( columnDefinition, header, 150, false );
	}

	/**
	 * Create column attributes.
	 *
	 * @param columnDefinitions
	 *            the definition
	 * @param headers
	 *            the header
	 * @param width
	 *            the column width
	 * @param resizable
	 *            is resizable
	 * @return the column attributes
	 */
	public static final ColumnAttributes[] columns( final ColumnDefinition< ? >[] columnDefinitions,
			final Object[] headers, final int width, final boolean resizable )
	{
		final ColumnAttributes[] results = new ColumnAttributes[columnDefinitions.length];

		for ( int i = 0; i < results.length; i++ )
		{
			results[i] = column( columnDefinitions[i], headers[i], width, resizable );
		}

		return results;
	}

	/**
	 * Create column attributes.
	 *
	 * @param <T>
	 *            the column type
	 * @param columnDefinition
	 *            the definition
	 * @param handler
	 *            the resource handler
	 * @param width
	 *            the column width
	 * @param resizable
	 *            is resizable
	 * @return the column attributes
	 */
	public static final < T > ColumnAttributes column( final ColumnDefinition< T > columnDefinition,
			final ResourceHandler handler, final int width, final boolean resizable )
	{
		final Object header = LocalisationHelper.getLocalisedName( handler, columnDefinition.getIdentifier() );
		return column( columnDefinition, header, width, resizable );
	}

	/**
	 * Create column attributes.
	 *
	 * @param <T>
	 *            the column type
	 * @param columnDefinition
	 *            the definition
	 * @param bundle
	 *            the resource bundle
	 * @return the column attributes
	 */
	public static final < T > ColumnAttributes column( final ColumnDefinition< T > columnDefinition,
			final ResourceBundle bundle )
	{
		return column( columnDefinition, bundle );
	}

	/**
	 * Create column attributes.
	 *
	 * @param columnDefinitions
	 *            the definition
	 * @param bundle
	 *            the resource bundle
	 * @param width
	 *            the column width
	 * @param resizable
	 *            is resizable
	 * @return the column attributes
	 */
	public static final ColumnAttributes[] columns( final ColumnDefinition< ? >[] columnDefinitions,
			final ResourceBundle bundle, final int width, final boolean resizable )
	{
		final ColumnAttributes[] results = new ColumnAttributes[columnDefinitions.length];

		for ( int i = 0; i < results.length; i++ )
		{
			results[i] = column( columnDefinitions[i], bundle, width, resizable );
		}

		return results;
	}

	/**
	 * Create a set of column attributes.
	 *
	 * @param columnDefinitions
	 *            set of column definitions
	 * @param widths
	 *            the column widths
	 * @param bundle
	 *            the resource bundle
	 * @param width
	 *            the column width
	 * @param resizable
	 *            is resizable
	 * @return the column attributes
	 */
	public static final ColumnAttributes[] columns( final ColumnDefinition< ? >[] columnDefinitions, final int[] widths,
			final ResourceBundle bundle, final int width, final boolean resizable )
	{
		final ColumnAttributes[] results = new ColumnAttributes[columnDefinitions.length];

		for ( int i = 0; i < results.length; i++ )
		{
			results[i] = column( columnDefinitions[i], bundle, widths[i], resizable );
		}

		return results;
	}

}
