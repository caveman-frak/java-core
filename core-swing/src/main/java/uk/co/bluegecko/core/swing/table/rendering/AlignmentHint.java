/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


/**
 * Rendering hint for alignment.
 */
public class AlignmentHint extends RenderingHint< Alignment >
{

	private static final long serialVersionUID = 8106474105209670861L;

	/**
	 * Create a weighted hint for alignment.
	 * 
	 * @param weight
	 *            weighting of hint
	 * @param value
	 *            alignment to use
	 */
	public AlignmentHint( final HintWeight weight, final Alignment value )
	{
		super( RenderingType.ALIGNMENT, weight, value );
	}

}
