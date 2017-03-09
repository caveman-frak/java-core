package uk.co.bluegecko.core.swing.table.rendering;


/**
 * Hint weight.
 */
public enum HintWeight
{
	MIN_WEIGHT( 0.0f ), LOW_WEIGHT( 0.25f ), DEFAULT_WEIGHT( 0.5f ), SELECTED_WEIGHT( 0.7f ), HIGH_WEIGHT(
			0.75f ), FOCUSED_WEIGHT( 0.8f ), MAX_WEIGHT( 1.0f );

	private final float value;

	private HintWeight( final float value )
	{
		this.value = value;
	}

	public float getValue()
	{
		return value;
	}
}