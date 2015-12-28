/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Color;


/**
 * Rendering hint for background colour.
 */
public class BackgroundHint extends ColorHint
{

	private static final long serialVersionUID = -5383740516931881995L;

	/**
	 * Create a background hint.
	 *
	 * @param weight
	 *            weighting to use
	 * @param value
	 *            colour to use
	 */
	public BackgroundHint( final HintWeight weight, final Color value )
	{
		super( RenderingType.BACKGROUND, weight, value );
	}

	protected BackgroundHint( final HintWeight weight )
	{
		super( RenderingType.BACKGROUND, weight );
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
		return new Darker( RenderingType.BACKGROUND, weight );
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
		return new Brighter( RenderingType.BACKGROUND, weight );
	}

}
