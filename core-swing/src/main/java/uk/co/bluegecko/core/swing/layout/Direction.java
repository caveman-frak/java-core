package uk.co.bluegecko.core.swing.layout;


/**
 * Scaling direction for components.
 */
public enum Direction
{
	HORIZONTAL( true, false ), VERTICAL( false, true ), BOTH( true, true );

	private final boolean overrideWidth;
	private final boolean overrideHeight;

	private Direction( final boolean overrideWidth, final boolean overrideHeight )
	{
		this.overrideWidth = overrideWidth;
		this.overrideHeight = overrideHeight;
	}

	public boolean overideWidth()
	{
		return overrideWidth;
	}

	public boolean overrideHeight()
	{
		return overrideHeight;
	}

}