package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Color;
import java.util.Collection;

import uk.co.bluegecko.core.swing.table.Table;


/**
 * Rendering rule for implementing row striping.
 */
public class RowStriping extends RenderingRule
{

	private final int interval;
	private final Color color;

	/**
	 * Create a row striping rule using the provider and striping pattern.
	 * 
	 * @param provider
	 *            base provider
	 * @param interval
	 *            row interval
	 * @param color
	 *            stripe colour
	 */
	public RowStriping( final HintProvider provider, final int interval, final Color color )
	{
		super( provider );

		this.interval = interval;
		this.color = color;
	}

	/**
	 * Create a row striping rule using the striping pattern.
	 * 
	 * @param interval
	 *            row interval
	 * @param color
	 *            stripe colour
	 */
	public RowStriping( final int interval, final Color color )
	{
		this( null, interval, color );
	}

	/**
	 * Create a row striping rule using the provider and default striping pattern.
	 * 
	 * @param provider
	 *            base provider
	 */
	public RowStriping( final HintProvider provider )
	{
		this( provider, 2, new Color( 0xE8FFBF ) );
	}

	/**
	 * Zero argument constructor.
	 */
	public RowStriping()
	{
		this( null );
	}

	@Override
	protected Collection< RenderingHint< ? > > processRenderingHints( final Collection< RenderingHint< ? > > hints,
			final Table table, final int rowIndex, final int columnIndex, final boolean isSelected,
			final boolean isFocused )
	{
		addBackground( hints, rowIndex );
		return hints;
	}

	private final void addBackground( final Collection< RenderingHint< ? > > hints, final int rowIndex )
	{
		if ( rowIndex % interval == 0 )
		{
			hints.add( new BackgroundHint( HintWeight.DEFAULT_WEIGHT, color ) );
		}
	}

}
