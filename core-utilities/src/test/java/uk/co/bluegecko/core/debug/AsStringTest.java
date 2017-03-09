package uk.co.bluegecko.core.debug;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.debug.AsString.asString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.test.source.NumericSequenceSource;
import uk.co.bluegecko.core.test.source.Source;
import uk.co.bluegecko.core.test.source.WordSequenceSource;


public class AsStringTest
{

	private static final int SIZE = 5;
	private List< String > list;
	private Map< Integer, String > map;
	private String[] array;

	@Before
	public final void setUp()
	{
		final Source< String > strings = new WordSequenceSource();
		final Source< Integer > integers = new NumericSequenceSource();

		list = new ArrayList<>();
		map = new HashMap<>();
		array = new String[SIZE];

		for ( int i = 0; i < SIZE; i++ )
		{
			final String word = strings.next();
			map.put( integers.next(), word );
			list.add( word );
			array[i] = word;
		}
	}

	@Test
	public void testObject()
	{
		assertThat( asString( SIZE ), is( "5" ) );
	}

	@Test
	public void testCollection()
	{
		assertThat( asString( list ), is( "{One,Two,Three,Four,Five}" ) );
	}

	@Test
	public void testMap()
	{
		assertThat( asString( map ), is( "<(1:One),(2:Two),(3:Three),(4:Four),(5:Five)>" ) );
	}

	@Test
	public void testArray()
	{
		assertThat( asString( array ), is( "[One,Two,Three,Four,Five]" ) );
	}

}
