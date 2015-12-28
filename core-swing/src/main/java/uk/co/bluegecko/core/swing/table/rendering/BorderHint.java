/*
 * Created on 04-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import javax.swing.border.Border;


/**
 * Rendering hint for border.
 */
public class BorderHint extends RenderingHint< Border >
{

	private static final long serialVersionUID = 3896501292335647864L;

	/**
	 * Create a border hint.
	 *
	 * @param weight
	 *            weighting to use
	 * @param value
	 *            border to use
	 */
	public BorderHint( final HintWeight weight, final Border value )
	{
		super( RenderingType.BORDER, weight, value );
	}

	/**
	 * Create a border of none hint.
	 *
	 * @param weight
	 *            weighting to use
	 * @return border hint
	 */
	public static final BorderHint none( final HintWeight weight )
	{
		return new BorderHint( weight, null );
	}

}
