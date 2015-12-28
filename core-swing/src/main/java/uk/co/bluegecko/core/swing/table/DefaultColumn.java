package uk.co.bluegecko.core.swing.table;


import java.util.Comparator;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


/**
 * Concrete implementation of a {@link ColumnAttributes} and {@link ColumnDefinition}.
 *
 * @param <T>
 *            the data type
 */
public class DefaultColumn< T > implements ColumnAttributes, ColumnDefinition< T >
{

	private final String identifier;
	private final Object header;
	private final Class< T > klass;
	private final Comparator< T > comparator;
	private final boolean editable;
	private final TableCellRenderer headerRenderer;
	private final TableCellRenderer cellRenderer;
	private final TableCellEditor cellEditor;
	private final int minWidth;
	private final int preferredWidth;
	private final int maxWidth;
	private final boolean resizable;
	private int width;
	private boolean visible;

	/**
	 * Create column attributes and definition.
	 *
	 * @param identifier
	 *            the column identifier
	 * @param header
	 *            the header
	 * @param klass
	 *            the column type
	 * @param comparator
	 *            the column comparator
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
	public DefaultColumn( final String identifier, final Object header, final Class< T > klass,
			final Comparator< T > comparator, final TableCellRenderer headerRenderer,
			final TableCellRenderer cellRenderer, final TableCellEditor cellEditor, final int minWidth,
			final int preferredWidth, final int maxWidth, final boolean resizable, final boolean editable )
	{
		super();

		this.identifier = identifier;
		this.header = header;
		this.klass = klass;
		this.comparator = comparator;
		this.headerRenderer = headerRenderer;
		this.cellRenderer = cellRenderer;
		this.cellEditor = cellEditor;
		this.minWidth = minWidth;
		this.preferredWidth = preferredWidth;
		this.maxWidth = maxWidth;
		this.width = preferredWidth;
		this.resizable = resizable;
		this.editable = editable;
		this.visible = true;
	}

	/**
	 * Create column attributes and definition.
	 *
	 * @param identifier
	 *            the column identifier
	 * @param header
	 *            the header
	 * @param klass
	 *            the column type
	 * @param width
	 *            the column width
	 * @param resizable
	 *            is resizable
	 * @param editable
	 *            is editable
	 */
	public DefaultColumn( final String identifier, final Object header, final Class< T > klass, final int width,
			final boolean resizable, final boolean editable )
	{
		this( identifier, header, klass, null, null, null, null, 0, width, Integer.MAX_VALUE, resizable, editable );
	}

	@Override
	public String getIdentifier()
	{
		return identifier;
	}

	@Override
	public Class< T > getColumnClass()
	{
		return klass;
	}

	@Override
	public Comparator< T > getComparator()
	{
		return comparator;
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
		return editable;
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

}
