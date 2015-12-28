/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Font;


/**
 * Rendering hint for font.
 */
public class FontHint extends RenderingHint< Font >
{

	private static final long serialVersionUID = 8106474105209670861L;

	/**
	 * Create a foreground hint.
	 *
	 * @param weight
	 *            weighting to use
	 * @param value
	 *            font to use
	 */
	public FontHint( final HintWeight weight, final Font value )
	{
		super( RenderingType.FONT, weight, value );
	}

	protected FontHint( final HintWeight weight )
	{
		super( RenderingType.FONT, weight );
	}

	/**
	 * Create a derived font hint, applying the supplied style.
	 * 
	 * @param weight
	 *            weighing to use
	 * @param style
	 *            style to apply
	 * @return derived font hint
	 */
	public static FontHint style( final HintWeight weight, final int style )
	{
		return new FontHint( weight)
		{

			private static final long serialVersionUID = 4002633885916705514L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( style );
			}
		};
	}

	/**
	 * Create a derived font hint, applying the supplied style and size.
	 * 
	 * @param weight
	 *            weighing to use
	 * @param style
	 *            style to apply
	 * @param size
	 *            size to use
	 * @return derived font hint
	 */
	public static FontHint style( final HintWeight weight, final int style, final float size )
	{
		return new FontHint( weight)
		{

			private static final long serialVersionUID = 4002633885916705514L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( style, size );
			}
		};
	}

	/**
	 * Create a derived font hint, applying the supplied size.
	 * 
	 * @param weight
	 *            weighing to use
	 * @param size
	 *            size to use
	 * @return derived font hint
	 */
	public static FontHint size( final HintWeight weight, final float size )
	{
		return new FontHint( weight)
		{

			private static final long serialVersionUID = 4002633885916705514L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( size );
			}
		};
	}

	/**
	 * Create a derived font hint, applying the supplied size increment.
	 * 
	 * @param weight
	 *            weighing to use
	 * @param size
	 *            size to increment
	 * @return derived font hint
	 */
	public static FontHint larger( final HintWeight weight, final float size )
	{
		return new FontHint( weight)
		{

			private static final long serialVersionUID = 4002633885916705514L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( original.getSize2D() + size );
			}
		};
	}

	/**
	 * Create a derived font hint, applying the supplied size decrement.
	 * 
	 * @param weight
	 *            weighing to use
	 * @param size
	 *            size to decrement
	 * @return derived font hint
	 */
	public static FontHint smaller( final HintWeight weight, final float size )
	{
		return new FontHint( weight)
		{

			private static final long serialVersionUID = 4002633885916705514L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( original.getSize2D() - size );
			}
		};
	}

	/**
	 * Create a derived font hint, applying the supplied size scaling.
	 * 
	 * @param weight
	 *            weighing to use
	 * @param scaling
	 *            scaling to apply to size
	 * @return derived font hint
	 */
	public static FontHint scaled( final HintWeight weight, final float scaling )
	{
		return new FontHint( weight)
		{

			private static final long serialVersionUID = 4002633885916705514L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( original.getSize2D() * scaling );
			}
		};
	}

}
