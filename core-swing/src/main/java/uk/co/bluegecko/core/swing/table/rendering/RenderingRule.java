package uk.co.bluegecko.core.swing.table.rendering;


import java.util.ArrayList;
import java.util.Collection;

import uk.co.bluegecko.core.swing.table.Table;


/**
 * A rendering rule for applying hints.
 */
public abstract class RenderingRule implements HintProvider
{

	private final HintProvider hintProvider;

	/**
	 * Rendering rule using a hint provider.
	 *
	 * @param hintProvider
	 *            provider to use
	 */
	public RenderingRule( final HintProvider hintProvider )
	{
		super();

		this.hintProvider = hintProvider;
	}

	/**
	 * Rendering rule.
	 */
	public RenderingRule()
	{
		this( null );
	}

	@Override
	public final Collection< RenderingHint< ? > > getHeaderHints( final Table table, final int rowIndex,
			final int columnIndex, final boolean isSelected )
	{
		final Collection< RenderingHint< ? > > hints = hintProvider != null
				? hintProvider.getHeaderHints( table, rowIndex, columnIndex, isSelected ) : getEmptyHints();

		return processHeaderHints( hints, table, rowIndex, columnIndex, isSelected );
	}

	@Override
	public final Collection< RenderingHint< ? > > getRenderingHints( final Table table, final int rowIndex,
			final int columnIndex, final boolean isSelected, final boolean isFocused )
	{
		final Collection< RenderingHint< ? > > hints = hintProvider != null
				? hintProvider.getRenderingHints( table, rowIndex, columnIndex, isSelected, isFocused )
				: getEmptyHints();

		return processRenderingHints( hints, table, rowIndex, columnIndex, isSelected, isFocused );
	}

	@Override
	public final Collection< RenderingHint< ? > > getEditingHints( final Table table, final int rowIndex,
			final int columnIndex, final boolean isSelected )
	{
		final Collection< RenderingHint< ? > > hints = hintProvider != null
				? hintProvider.getHeaderHints( table, rowIndex, columnIndex, isSelected ) : getEmptyHints();

		return processEditingHints( hints, table, rowIndex, columnIndex, isSelected );
	}

	/**
	 * Process hints for the header cell.
	 *
	 * @param hints
	 *            hints to process
	 * @param table
	 *            the table
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 * @param isSelected
	 *            is the cell selected
	 * @return rendering hints
	 */
	protected Collection< RenderingHint< ? > > processHeaderHints( final Collection< RenderingHint< ? > > hints,
			final Table table, final int rowIndex, final int columnIndex, final boolean isSelected )
	{
		return hints;
	}

	/**
	 * Process hints for the cell.
	 * 
	 * @param hints
	 *            hints to process
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
	 * @return rendering hints
	 */
	protected Collection< RenderingHint< ? > > processRenderingHints( final Collection< RenderingHint< ? > > hints,
			final Table table, final int rowIndex, final int columnIndex, final boolean isSelected,
			final boolean isFocused )
	{
		return hints;
	}

	/**
	 * Process hints for the editing cell.
	 * 
	 * @param hints
	 *            hints to process
	 * @param table
	 *            the table
	 * @param rowIndex
	 *            the row index
	 * @param columnIndex
	 *            the column index
	 * @param isSelected
	 *            is the cell selected
	 * @return rendering hints
	 */
	protected Collection< RenderingHint< ? > > processEditingHints( final Collection< RenderingHint< ? > > hints,
			final Table table, final int rowIndex, final int columnIndex, final boolean isSelected )
	{
		return hints;
	}

	private final Collection< RenderingHint< ? > > getEmptyHints()
	{
		return new ArrayList< >();
	}

}
