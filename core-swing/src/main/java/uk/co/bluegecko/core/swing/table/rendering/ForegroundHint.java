/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Color;


/**
 * Rendering hint for foreground colour.
 */
public class ForegroundHint extends ColorHint
{

	private static final long serialVersionUID = -7290177529443626401L;

	/**
	 * Create a foreground hint.
	 *
	 * @param weight
	 *            weighting to use
	 * @param value
	 *            colour to use
	 */
	public ForegroundHint( final HintWeight weight, final Color value )
	{
		super( RenderingType.FOREGROUND, weight, value );
	}

	protected ForegroundHint( final HintWeight weight )
	{
		super( RenderingType.FOREGROUND, weight );
	}

	/**
	 * Create a derived hint to use a darker colour .
	 *
	 * @param weight
	 *            weighting to use
	 * @return derived hint
	 */
	public static ColorHint darker( final HintWeight weight )
	{
		return new Darker( RenderingType.FOREGROUND, weight );
	}

	/**
	 * Create a derived hint to use a brighter colour .
	 *
	 * @param weight
	 *            weighting to use
	 * @return derived hint
	 */
	public static ColorHint brighter( final HintWeight weight )
	{
		return new Brighter( RenderingType.FOREGROUND, weight );
	}

}
