/*
 * Created on 04-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


/**
 * Hint for row height.
 */
public class RowHeightHint extends RenderingHint< Integer >
{

	private static final long serialVersionUID = 3896501292335647864L;

	/**
	 * Create a row height hint.
	 * 
	 * @param weight
	 *            weighting to use
	 * @param value
	 *            row height to use
	 */
	public RowHeightHint( final HintWeight weight, final Integer value )
	{
		super( RenderingType.HEIGHT, weight, value );
	}

}
