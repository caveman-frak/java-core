package uk.co.bluegecko.core.swing.table.rendering;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.swing.table.Table;


@SuppressWarnings( "javadoc" )
public class RenderingHelperTest
{

	private Table table;

	@Before
	public final void setUp()
	{
		table = new Table();
	}

	@Test
	public final void testGetDefaultHint()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, false, false, false );
		assertEquals( "all", 3, results.size() );

		assertEquals( "foreground", 1, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 1, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 0, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertNull( "border.preferred", RenderingHelper.getPreferredHint( RenderingType.BORDER, results ) );
	}

	@Test
	public final void testGetDefaultHintSelected()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, true, false, false );
		assertEquals( "all", 5, results.size() );

		assertEquals( "foreground", 2, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 2, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 0, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertNull( "border.preferred", RenderingHelper.getPreferredHint( RenderingType.BORDER, results ) );
	}

	@Test
	public final void testGetDefaultHintFocused()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, false, true, false );
		assertEquals( "all", 6, results.size() );

		assertEquals( "foreground", 2, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 2, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 1, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.FOCUSED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.FOCUSED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertEquals( "border.preferred", HintWeight.FOCUSED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BORDER, results ).getWeight() );
	}

	@Test
	public final void testGetDefaultHintEditing()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, false, false, true );
		assertEquals( "all", 3, results.size() );

		assertEquals( "foreground", 1, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 1, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 0, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertNull( "border.preferred", RenderingHelper.getPreferredHint( RenderingType.BORDER, results ) );
	}

	@Test
	public final void testGetDefaultHintSelectedFocused()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, true, true, false );
		assertEquals( "all", 6, results.size() );

		assertEquals( "foreground", 2, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 2, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 1, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertEquals( "border.preferred", HintWeight.FOCUSED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BORDER, results ).getWeight() );
	}

	@Test
	public final void testGetDefaultHintSelectedEditing()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, true, false, true );
		assertEquals( "all", 5, results.size() );

		assertEquals( "foreground", 2, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 2, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 0, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertNull( "border.preferred", RenderingHelper.getPreferredHint( RenderingType.BORDER, results ) );
	}

	@Test
	public final void testGetDefaultHintFocusedEditing()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, false, true, true );
		assertEquals( "all", 3, results.size() );

		assertEquals( "foreground", 1, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 1, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 0, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertNull( "border.preferred", RenderingHelper.getPreferredHint( RenderingType.BORDER, results ) );
	}

	@Test
	public final void testGetDefaultHintSelectedFocusedEditing()
	{
		final Collection< RenderingHint< ? > > results = RenderingHelper.getDefaultHints( table, true, true, true );
		assertEquals( "all", 5, results.size() );

		assertEquals( "foreground", 2, RenderingHelper.getHints( RenderingType.FOREGROUND, results ).size() );
		assertEquals( "background", 2, RenderingHelper.getHints( RenderingType.BACKGROUND, results ).size() );
		assertEquals( "font", 1, RenderingHelper.getHints( RenderingType.FONT, results ).size() );
		assertEquals( "border", 0, RenderingHelper.getHints( RenderingType.BORDER, results ).size() );

		assertEquals( "foreground.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FOREGROUND, results ).getWeight() );
		assertEquals( "background.preferred", HintWeight.SELECTED_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.BACKGROUND, results ).getWeight() );
		assertEquals( "font.preferred", HintWeight.MIN_WEIGHT,
				RenderingHelper.getPreferredHint( RenderingType.FONT, results ).getWeight() );
		assertNull( "border.preferred", RenderingHelper.getPreferredHint( RenderingType.BORDER, results ) );
	}

}
