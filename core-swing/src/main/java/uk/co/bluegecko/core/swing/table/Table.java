package uk.co.bluegecko.core.swing.table;


import java.awt.Component;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import uk.co.bluegecko.core.swing.table.rendering.HintProvider;
import uk.co.bluegecko.core.swing.table.rendering.RenderingHelper;
import uk.co.bluegecko.core.swing.table.rendering.RenderingHint;
import uk.co.bluegecko.core.swing.table.rendering.RowHeightHint;


/**
 * An implementation of {@link JTable} using data providers and column definitions.
 *
 */
public class Table extends JTable
{

	private static final long serialVersionUID = -7733889046041252487L;

	private HintProvider hintProvider;

	/**
	 * Create a new table.
	 *
	 * @param name
	 *            the table name
	 */
	public Table( final String name )
	{
		super();

		setName( name );
		setFillsViewportHeight( true );
		setAutoscrolls( true );
		setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		setAutoCreateRowSorter( false );
	}

	/**
	 * Create a new table.
	 */
	public Table()
	{
		this( "undefined" );
	}

	/**
	 * Get the hint provider.
	 *
	 * @return the hint provider
	 */
	public final HintProvider getHintProvider()
	{
		return hintProvider;
	}

	/**
	 * Set the hint provider.
	 *
	 * @param hintProvider
	 *            the hint provider
	 */
	public final synchronized void setHintProvider( final HintProvider hintProvider )
	{
		if ( this.hintProvider != hintProvider )
		{
			this.hintProvider = hintProvider;
			invalidate();
		}
	}

	@Override
	public Component prepareRenderer( final TableCellRenderer renderer, final int rowIndex, final int columnIndex )
	{
		final Component component = super.prepareRenderer( renderer, rowIndex, columnIndex );

		if ( hintProvider != null )
		{
			final boolean isSelected = isCellSelected( rowIndex, columnIndex );
			final boolean isFocused = isCellFocused( rowIndex, columnIndex );

			final Collection< RenderingHint< ? > > hints = hintProvider.getRenderingHints( this, rowIndex, columnIndex,
					isSelected, isFocused );
			RenderingHelper.updateComponent( this, component, isSelected, isFocused, false, hints );
		}

		return component;
	}

	@Override
	public Component prepareEditor( final TableCellEditor editor, final int rowIndex, final int columnIndex )
	{
		final Component component = super.prepareEditor( editor, rowIndex, columnIndex );

		if ( hintProvider != null )
		{
			final boolean isSelected = isCellSelected( rowIndex, columnIndex );

			final Collection< RenderingHint< ? > > hints = hintProvider.getEditingHints( this, rowIndex, columnIndex,
					isSelected );
			RenderingHelper.updateComponent( this, component, isSelected, true, true, hints );
		}

		return component;
	}

	private final boolean isCellFocused( final int row, final int column )
	{
		final boolean rowIsLead = selectionModel.getLeadSelectionIndex() == row;
		final boolean colIsLead = columnModel.getSelectionModel().getLeadSelectionIndex() == column;

		return rowIsLead && colIsLead && isFocusOwner();
	}

	@Override
	public int getRowHeight( final int rowIndex )
	{
		int rowHeight = super.getRowHeight( rowIndex );

		if ( hintProvider != null )
		{
			final boolean isSelected = isCellSelected( rowIndex, 0 );
			final boolean isFocused = isCellFocused( rowIndex, 0 );

			final Collection< RenderingHint< ? > > hints = hintProvider.getRenderingHints( this, rowIndex, 0,
					isSelected, isFocused );

			final RowHeightHint hint = RenderingHelper.getRowHeight( this, isSelected, isFocused, isFocused, hints );
			if ( hint != null )
			{
				rowHeight = hint.getValue( rowHeight );
				/** need to set row height to make underlying table track row positions properly */
				setRowHeight( rowIndex, rowHeight );
				return rowHeight;
			}
		}
		return rowHeight;
	}

	/**
	 * Get a row of data.
	 * 
	 * @param rowIndex
	 *            the row index
	 * @return the data
	 */
	public Object getRow( final int rowIndex )
	{
		if ( getModel() instanceof DataSource< ? > )
		{
			final DataSource< ? > definition = ( DataSource< ? > ) getModel();

			return definition.getRow( convertRowIndexToModel( rowIndex ) );
		}
		return null;
	}

}
