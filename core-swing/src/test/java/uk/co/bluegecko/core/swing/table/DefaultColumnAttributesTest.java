package uk.co.bluegecko.core.swing.table;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class DefaultColumnAttributesTest
{

	private ColumnDefinition< String > definition;

	@Before
	public final void setUp()
	{
		definition = DefaultColumnDefinition.stringColumn( "ONE", false );
	}

	@Test
	public final void testUnadjustedWidths()
	{
		final ColumnAttributes attributes = createAttribute( 0, 100, 200 );
		assertEquals( "min", 0, attributes.getMinWidth() );
		assertEquals( "preferred", 100, attributes.getPreferredWidth() );
		assertEquals( "max", 200, attributes.getMaxWidth() );
		assertEquals( "width", 100, attributes.getWidth() );
	}

	@Test
	public final void testAdjustedPreferredWidths()
	{
		final ColumnAttributes attributes = createAttribute( 150, 100, 200 );
		assertEquals( "min", 150, attributes.getMinWidth() );
		assertEquals( "preferred", 150, attributes.getPreferredWidth() );
		assertEquals( "max", 200, attributes.getMaxWidth() );
		assertEquals( "width", 150, attributes.getWidth() );
	}

	@Test
	public final void testAdjustedMaxWidths()
	{
		final ColumnAttributes attributes = createAttribute( 0, 100, 50 );
		assertEquals( "min", 0, attributes.getMinWidth() );
		assertEquals( "preferred", 100, attributes.getPreferredWidth() );
		assertEquals( "max", 100, attributes.getMaxWidth() );
		assertEquals( "width", 100, attributes.getWidth() );
	}

	@Test
	public final void testAdjustedBothWidths()
	{
		final ColumnAttributes attributes = createAttribute( 250, 100, 200 );
		assertEquals( "min", 250, attributes.getMinWidth() );
		assertEquals( "preferred", 250, attributes.getPreferredWidth() );
		assertEquals( "max", 250, attributes.getMaxWidth() );
		assertEquals( "width", 250, attributes.getWidth() );
	}

	@Test
	public final void testSetWidth()
	{
		final ColumnAttributes attributes = createAttribute( 50, 100, 200 );

		attributes.setWidth( 60 );
		assertEquals( "unadjusted", 60, attributes.getWidth() );

		attributes.setWidth( 10 );
		assertEquals( "adjusted min", 50, attributes.getWidth() );

		attributes.setWidth( 210 );
		assertEquals( "adjusted max", 200, attributes.getWidth() );
	}

	private final DefaultColumnAttributes< String > createAttribute( final int minWidth, final int preferredWidth,
			final int maxWidth )
	{
		return new DefaultColumnAttributes<>( definition, "ONE", null, null, null, minWidth, preferredWidth, maxWidth,
				true, false );
	}

}
