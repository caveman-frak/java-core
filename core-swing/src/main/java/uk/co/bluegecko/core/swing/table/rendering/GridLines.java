package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Color;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import uk.co.bluegecko.core.swing.border.EdgeBorder;
import uk.co.bluegecko.core.swing.border.EdgeBorder.EdgeStyle;
import uk.co.bluegecko.core.swing.table.Table;


/**
 * Rendering rule for drawing grid lines.
 */
public class GridLines extends RenderingRule
{

	private final Border border;

	/**
	 * Create a grid lines rule using the provider and grid line parameters.
	 *
	 * @param provider
	 *            base provider
	 * @param horizontal
	 *            horizontal lines
	 * @param vertical
	 *            vertical lines
	 * @param color
	 *            stripe colour
	 */
	public GridLines( final HintProvider provider, final boolean horizontal, final boolean vertical, final Color color )
	{
		super( provider );

		border = BorderFactory.createCompoundBorder( new EdgeBorder( color, EdgeStyle.NONE, EdgeStyle.NONE,
				horizontal ? EdgeStyle.LINE : EdgeStyle.NONE, vertical ? EdgeStyle.LINE : EdgeStyle.NONE ),
				new EmptyBorder( 1, 1, 1, 1 ) );
	}

	/**
	 * Create a grid lines rule using the grid line parameters.
	 *
	 * @param horizontal
	 *            horizontal lines
	 * @param vertical
	 *            vertical lines
	 */
	public GridLines( final boolean horizontal, final boolean vertical )
	{
		this( null, horizontal, vertical, Color.LIGHT_GRAY );

	}

	/**
	 * Create a grid lines rule using the provider and default grid lines.
	 *
	 * @param provider
	 *            base provider
	 */
	public GridLines( final HintProvider provider )
	{
		this( provider, true, true, Color.LIGHT_GRAY );
	}

	/**
	 * Zero arg constructor.
	 */
	public GridLines()
	{
		this( null );
	}

	@Override
	protected Collection< RenderingHint< ? > > processRenderingHints( final Collection< RenderingHint< ? > > hints,
			final Table table, final int rowIndex, final int columnIndex, final boolean isSelected,
			final boolean isFocused )
	{
		hints.add( new BorderHint( HintWeight.DEFAULT_WEIGHT, border ) );
		return hints;
	}

}
