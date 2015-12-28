/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Color;


/**
 * Rendering hint for colour.
 */
public class ColorHint extends RenderingHint< Color >
{

	private static final long serialVersionUID = -7290177529443626401L;

	protected ColorHint( final RenderingType type, final HintWeight weight, final Color value )
	{
		super( type, weight, value );
	}

	protected ColorHint( final RenderingType type, final HintWeight weight )
	{
		super( type, weight );
	}

	protected static class Darker extends ColorHint
	{

		private static final long serialVersionUID = 4002633885916705514L;

		protected Darker( final RenderingType type, final HintWeight weight )
		{
			super( type, weight );
		}

		@Override
		protected Color derive( final Color original )
		{
			return original.darker();
		}
	}

	protected static class Brighter extends ColorHint
	{

		private static final long serialVersionUID = 4002633885916705514L;

		protected Brighter( final RenderingType type, final HintWeight weight )
		{
			super( type, weight );
		}

		@Override
		protected Color derive( final Color original )
		{
			return original.brighter();
		}
	}

}
