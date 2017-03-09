package uk.co.bluegecko.core.swing.table.rendering;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Font;

import org.junit.Before;
import org.junit.Test;


public class RenderingHintTest
{

	private Font font;
	private Color color;

	@Before
	public final void setUp()
	{
		font = Font.decode( "Monospaced-12" );
		color = new Color( 0x808080 );
	}

	@Test
	public final void testWeightExceeds()
	{
		final FontHint min = new FontHint( HintWeight.MIN_WEIGHT );
		final FontHint low = new FontHint( HintWeight.LOW_WEIGHT );
		final FontHint def = new FontHint( HintWeight.DEFAULT_WEIGHT );
		final FontHint selected = new FontHint( HintWeight.SELECTED_WEIGHT );
		final FontHint high = new FontHint( HintWeight.HIGH_WEIGHT );
		final FontHint focused = new FontHint( HintWeight.FOCUSED_WEIGHT );
		final FontHint max = new FontHint( HintWeight.MAX_WEIGHT );

		assertFalse( "min-min", min.exceeds( min ) );
		assertFalse( "min-low", min.exceeds( low ) );
		assertTrue( "low-min", low.exceeds( min ) );
		assertTrue( "default-low", def.exceeds( low ) );
		assertTrue( "selected-default", selected.exceeds( def ) );
		assertTrue( "high-selected", high.exceeds( selected ) );
		assertTrue( "focused-high", focused.exceeds( high ) );
		assertTrue( "max-focused", max.exceeds( focused ) );
	}

	@Test
	public final void testGetValueNone()
	{
		assertEquals( font, new FontHint( HintWeight.MAX_WEIGHT ).getValue( font ) );
		assertNull( new FontHint( HintWeight.MAX_WEIGHT ).getValue() );
	}

	@Test
	public final void testGetValueNonDerived()
	{
		final Font value = Font.decode( "Monospaced-BOLD-14" );
		assertEquals( value, new FontHint( HintWeight.MAX_WEIGHT, value ).getValue( font ) );
		assertEquals( value, new FontHint( HintWeight.MAX_WEIGHT, value ).getValue() );
	}

	@Test
	public final void testGetValueDerived()
	{
		final Font value = Font.decode( "Monospaced-14" );
		final FontHint fontHint = new FontHint( HintWeight.MAX_WEIGHT )
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected Font derive( final Font original )
			{
				return original.deriveFont( 14.0f );
			}
		};
		assertEquals( value, fontHint.getValue( font ) );
		assertNull( fontHint.getValue() );
	}

	@Test
	public final void testFontHintSize()
	{
		final Font value = Font.decode( "Monospaced-14" );
		assertEquals( value, FontHint.size( HintWeight.MAX_WEIGHT, 14 )
				.getValue( font ) );
	}

	@Test
	public final void testFontHintLarger()
	{
		final Font value = Font.decode( "Monospaced-14" );
		assertEquals( value, FontHint.larger( HintWeight.MAX_WEIGHT, 2 )
				.getValue( font ) );
	}

	@Test
	public final void testFontHintSmaller()
	{
		final Font value = Font.decode( "Monospaced-10" );
		assertEquals( value, FontHint.smaller( HintWeight.MAX_WEIGHT, 2 )
				.getValue( font ) );
	}

	@Test
	public final void testFontHintScaled()
	{
		final Font value = Font.decode( "Monospaced-6" );
		assertEquals( value, FontHint.scaled( HintWeight.MAX_WEIGHT, 0.5f )
				.getValue( font ) );
	}

	@Test
	public final void testFontHintStyle()
	{
		final Font value = Font.decode( "Monospaced-BOLD-12" );
		assertEquals( value, FontHint.style( HintWeight.MAX_WEIGHT, Font.BOLD )
				.getValue( font ) );
	}

	@Test
	public final void testFontHintStyleAndSize()
	{
		final Font value = Font.decode( "Monospaced-BOLD-14" );
		assertEquals( value, FontHint.style( HintWeight.MAX_WEIGHT, Font.BOLD, 14 )
				.getValue( font ) );
	}

	@Test
	public final void testForegroundHintDarker()
	{
		final Color value = new Color( 0x595959 );
		assertEquals( value, ForegroundHint.darker( HintWeight.MAX_WEIGHT )
				.getValue( color ) );
	}

	@Test
	public final void testForegroundHintBrighter()
	{
		final Color value = new Color( 0xB6B6B6 );
		assertEquals( value, ForegroundHint.brighter( HintWeight.MAX_WEIGHT )
				.getValue( color ) );
	}

	@Test
	public final void testBackgroundHintDarker()
	{
		final Color value = new Color( 0x595959 );
		assertEquals( value, BackgroundHint.darker( HintWeight.MAX_WEIGHT )
				.getValue( color ) );
	}

	@Test
	public final void testBackgroundHintBrighter()
	{
		final Color value = new Color( 0xB6B6B6 );
		assertEquals( value, BackgroundHint.brighter( HintWeight.MAX_WEIGHT )
				.getValue( color ) );
	}

}
