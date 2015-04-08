package uk.co.bluegecko.core.builder;


import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.caught;
import static uk.co.bluegecko.core.test.matcher.map.EntryMatcher.entry;
import static uk.co.bluegecko.core.test.matcher.map.MapContains.contains;
import static uk.co.bluegecko.core.test.matcher.map.MapHasEntries.hasEntries;
import static uk.co.bluegecko.core.test.matcher.map.MapSize.hasMapSize;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class MapBuilderTest
{

	@Test
	public void testMap()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		builder.with( 10, "Int" ).with( 10L, "Long" ).with( 10.01F, "Float" ).with( 10.01D, "Double" );
		final Map< Number, String > map = builder.build();
		assertThat( map, hasMapSize( 4 ) );
		assertThat(
				map,
				hasEntries( entry( 10, "Int" ), entry( 10L, "Long" ), entry( 10.01F, "Float" ),
						entry( 10.01D, "Double" ) ) );
	}

	@Test
	public void testWithArray()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		builder.with( new Integer[]
			{ 10, 11, 12, 13 }, new String[]
			{ "10", "11", "12", "13" } );
		final Map< Number, String > map = builder.build();
		assertThat( map, hasMapSize( 4 ) );
		assertThat( map,
				contains( entry( 10, "10" ), entry( 11, "11" ), entry( 12, "12" ), entry( 13, "13" ) ) );
	}

	@Test
	public void testWithArrayMisMatch()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		capture( ( ) -> builder.with( new Integer[]
			{ 10, 11, 12, 13 }, new String[]
			{ "10", "11", "12" } ) );
		final Map< Number, String > map = builder.build();
		assertThat( map, hasMapSize( 0 ) );

		assertThat( caught(), is( instanceOf( IllegalArgumentException.class ) ) );
	}

	@Test
	public void testWithList()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		builder.with( Arrays.asList( 10, 11, 12, 13 ), Arrays.asList( "10", "11", "12", "13" ) );
		final Map< Number, String > map = builder.build();
		assertThat( map, hasMapSize( 4 ) );
		assertThat( map,
				contains( entry( 10, "10" ), entry( 11, "11" ), entry( 12, "12" ), entry( 13, "13" ) ) );
	}

	@Test
	public void testWithListMisMatch()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		capture( ( ) -> builder.with( Arrays.asList( 10, 11, 12 ), Arrays.asList( "10", "11", "12", "13" ) ) );
		final Map< Number, String > map = builder.build();
		assertThat( map, hasMapSize( 0 ) );

		assertThat( caught(), is( instanceOf( IllegalArgumentException.class ) ) );
	}

	@Test
	public void testCopy()
	{
		final Map< Number, String > map2 = new TreeMap<>();
		final MapBuilder< Number, String > builder = MapBuilder.map( map2 );

		builder.with( 10, "10" ).with( 11, "11" );

		final MapBuilder< Number, String > builder2 = builder.copy();
		builder2.with( 12, "12" ).with( 13, "13" );

		final Map< Number, String > map = builder2.build();
		assertThat( builder.build(), is( not( sameInstance( map ) ) ) );
		assertThat( map, is( instanceOf( TreeMap.class ) ) );
		assertThat( map, hasMapSize( 4 ) );
		assertThat( map,
				contains( entry( 10, "10" ), entry( 11, "11" ), entry( 12, "12" ), entry( 13, "13" ) ) );
	}

	@Test
	public void testCopyWith()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		builder.with( 10, "10" ).with( 11, "11" );

		final Map< Number, String > map2 = new TreeMap<>();
		final MapBuilder< Number, String > builder2 = builder.copy( map2 );
		builder2.with( 12, "12" ).with( 13, "13" );

		final Map< Number, String > map = builder2.build();
		assertThat( map, is( sameInstance( map ) ) );
		assertThat( map, is( instanceOf( TreeMap.class ) ) );
		assertThat( map, hasMapSize( 4 ) );
		assertThat( map,
				contains( entry( 10, "10" ), entry( 11, "11" ), entry( 12, "12" ), entry( 13, "13" ) ) );
	}

	@Test
	public void testReset()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		builder.with( 10, "Int" ).with( 10L, "Long" ).with( 10.01F, "Float" ).with( 10.01D, "Double" );
		builder.reset();

		final Map< Number, String > map = builder.build();
		assertThat( map, hasMapSize( 0 ) );
	}

	@Test
	public void testBuildWith()
	{
		final MapBuilder< Number, String > builder = MapBuilder.map();

		builder.with( 10, "10" ).with( 11, "11" ).with( 12, "12" ).with( 13, "13" );

		final Map< Number, String > map = builder.build( new TreeMap<>() );
		assertThat( map, is( instanceOf( TreeMap.class ) ) );
		assertThat( map, hasMapSize( 4 ) );
		assertThat( map,
				contains( entry( 10, "10" ), entry( 11, "11" ), entry( 12, "12" ), entry( 13, "13" ) ) );
	}

}
