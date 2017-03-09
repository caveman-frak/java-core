package uk.co.bluegecko.core.util;


import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


public class SafeTest
{

	@Test
	public final void testEquals()
	{
		assertTrue( "both null", Safe.equals( null, null ) );
		assertFalse( "left null", Safe.equals( null, "ONE" ) );
		assertFalse( "left null", Safe.equals( "ONE", null ) );
		assertTrue( "same", Safe.equals( "ONE", "ONE" ) );
		assertFalse( "different", Safe.equals( "ONE", "TWO" ) );
	}

	@Test
	public final void testEqualsIgnoreCase()
	{
		assertTrue( "both null", Safe.equalsIgnoreCase( null, null ) );
		assertFalse( "left null", Safe.equalsIgnoreCase( null, "ONE" ) );
		assertFalse( "left null", Safe.equalsIgnoreCase( "ONE", null ) );
		assertTrue( "same - u/l", Safe.equalsIgnoreCase( "ONE", "one" ) );
		assertTrue( "same - l/l", Safe.equalsIgnoreCase( "one", "one" ) );
		assertTrue( "same - u/u", Safe.equalsIgnoreCase( "ONE", "ONE" ) );
		assertTrue( "same - m/m", Safe.equalsIgnoreCase( "One", "oNe" ) );
		assertFalse( "different", Safe.equalsIgnoreCase( "ONE", "TWO" ) );
	}

	@Test
	public final void testSame()
	{
		assertTrue( "both null", Safe.same( null, null ) );
		assertFalse( "left null", Safe.same( null, "ONE" ) );
		assertFalse( "left null", Safe.same( "ONE", null ) );
		assertTrue( "same", Safe.same( "ONE", "ONE" ) );
		final String one = "ONE", two = "one".toUpperCase();
		assertFalse( "same", Safe.same( one, two ) );
		assertTrue( "equal", Safe.equals( one, two ) );
		assertFalse( "different", Safe.same( "ONE", "TWO" ) );
	}

	@Test
	public final void testToString()
	{
		assertEquals( "null", null, Safe.toString( null ) );
		assertEquals( "not null", "ONE", Safe.toString( "ONE" ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testHashCode()
	{
		assertEquals( "null", 0, Safe.hashCode( null ) );
		assertThat( "not null", Safe.hashCode( "ONE" ), greaterThan( 0 ) );
	}

	@SuppressWarnings( "boxing" )
	@Test
	public final void testCompare()
	{
		assertEquals( "both null", 0, Safe.compare( null, null ) );
		assertEquals( "left null", -1, Safe.compare( null, "ONE" ) );
		assertEquals( "left null", 1, Safe.compare( "ONE", null ) );
		assertEquals( "same", 0, Safe.compare( "ONE", "ONE" ) );
		assertThat( "different", Safe.compare( "ONE", "TWO" ), lessThan( 0 ) );
	}

	@Test
	public final void testGetSizeString()
	{
		assertEquals( "null", 0, Safe.length( ( String ) null ) );
		assertEquals( "empty", 0, Safe.length( "" ) );
		assertEquals( "spaces", 4, Safe.length( "    " ) );
		assertEquals( "whitespace", 3, Safe.length( "\t\n\r" ) );
		assertEquals( "text", 5, Safe.length( "  A  " ) );
	}

	@Test
	public final void testGetSizeStringTrimmed()
	{
		assertEquals( "null", 0, Safe.length( ( String ) null ) );
		assertEquals( "empty", 0, Safe.length( "".trim() ) );
		assertEquals( "spaces", 0, Safe.length( "    ".trim() ) );
		assertEquals( "whitespace", 0, Safe.length( "\t\n\r".trim() ) );
		assertEquals( "text", 1, Safe.length( "  A  ".trim() ) );
	}

	@Test
	public final void testGetSizeArray()
	{
		assertEquals( "null", 0, Safe.length( ( String[] ) null ) );
		assertEquals( "empty", 0, Safe.length( new String[0] ) );
		assertEquals( "populated", 1, Safe.length( new String[]
			{ "ONE" } ) );
	}

	@Test
	public final void testGetSizeCollection()
	{
		Collection< String > collection = null;
		assertEquals( "null", 0, Safe.length( collection ) );
		collection = new ArrayList<>();
		assertEquals( "empty", 0, Safe.length( collection ) );
		collection.add( "ONE" );
		assertEquals( "populated", 1, Safe.length( collection ) );
	}

	@Test
	public final void testGetSizeMap()
	{
		Map< String, String > map = null;
		assertEquals( "null", 0, Safe.length( map ) );
		map = new HashMap<>();
		assertEquals( "empty", 0, Safe.length( map ) );
		map.put( "ONE", "ONE" );
		assertEquals( "populated", 1, Safe.length( map ) );
	}

	@Test
	public final void testIsEmptyString()
	{
		assertTrue( "null", Safe.isEmpty( ( String ) null ) );
		assertTrue( "empty", Safe.isEmpty( "" ) );
		assertFalse( "spaces", Safe.isEmpty( "    " ) );
		assertFalse( "whitespace", Safe.isEmpty( "\t\n\r" ) );
		assertFalse( "text", Safe.isEmpty( "  A  " ) );
	}

	@Test
	public final void testIsEmptyArray()
	{
		assertTrue( "null", Safe.isEmpty( ( String[] ) null ) );
		assertTrue( "empty", Safe.isEmpty( new String[0] ) );
		assertFalse( "populated", Safe.isEmpty( new String[]
			{ "ONE" } ) );
	}

	@Test
	public final void testIsEmptyCollection()
	{
		Collection< String > collection = null;
		assertTrue( "null", Safe.isEmpty( collection ) );
		collection = new ArrayList<>();
		assertTrue( "empty", Safe.isEmpty( collection ) );
		collection.add( "ONE" );
		assertFalse( "populated", Safe.isEmpty( collection ) );
	}

	@Test
	public final void testIsEmptyMap()
	{
		Map< String, String > map = null;
		assertTrue( "null", Safe.isEmpty( map ) );
		map = new HashMap<>();
		assertTrue( "empty", Safe.isEmpty( map ) );
		map.put( "ONE", "ONE" );
		assertFalse( "populated", Safe.isEmpty( map ) );
	}

	@Test
	public final void testIsBlankString()
	{
		assertFalse( "null", Safe.isBlank( ( String ) null ) );
		assertTrue( "empty", Safe.isBlank( "" ) );
		assertTrue( "spaces", Safe.isBlank( "    " ) );
		assertTrue( "whitespace", Safe.isBlank( "\t\n\r" ) );
		assertFalse( "text", Safe.isBlank( "  A  " ) );
	}

	@Test
	public final void testIsBlankArray()
	{
		assertFalse( "null", Safe.isBlank( ( String[] ) null ) );
		assertTrue( "empty", Safe.isBlank( new String[0] ) );
		assertFalse( "populated", Safe.isBlank( new String[]
			{ "ONE" } ) );
	}

	@Test
	public final void testIsBlankCollection()
	{
		Collection< String > collection = null;
		assertFalse( "null", Safe.isBlank( collection ) );
		collection = new ArrayList<>();
		assertTrue( "empty", Safe.isBlank( collection ) );
		collection.add( "ONE" );
		assertFalse( "populated", Safe.isBlank( collection ) );
	}

	@Test
	public final void testIsBlankMap()
	{
		Map< String, String > map = null;
		assertFalse( "null", Safe.isBlank( map ) );
		map = new HashMap<>();
		assertTrue( "empty", Safe.isBlank( map ) );
		map.put( "ONE", "ONE" );
		assertFalse( "populated", Safe.isBlank( map ) );
	}

	@Test
	public final void testCopyBooleanArray()
	{
		final boolean[] bools = new boolean[]
			{ true, false };
		assertThat( Safe.copy( bools ), is( not( sameInstance( bools ) ) ) );
		assertThat( Safe.copy( bools ), is( bools ) );
	}

	@Test
	public final void testCopyCharArray()
	{
		final char[] chars = new char[]
			{ 'A', 'B' };
		assertThat( Safe.copy( chars ), is( not( sameInstance( chars ) ) ) );
		assertThat( Safe.copy( chars ), is( chars ) );
	}

	@Test
	public final void testCopyByteArray()
	{
		final byte[] bytes = new byte[]
			{ 1, 5 };
		assertThat( Safe.copy( bytes ), is( not( sameInstance( bytes ) ) ) );
		assertThat( Safe.copy( bytes ), is( bytes ) );
	}

	@Test
	public final void testCopyShortArray()
	{
		final short[] shorts = new short[]
			{ 1, 5 };
		assertThat( Safe.copy( shorts ), is( not( sameInstance( shorts ) ) ) );
		assertThat( Safe.copy( shorts ), is( shorts ) );
	}

	@Test
	public final void testCopyIntegerArray()
	{
		final int[] ints = new int[]
			{ 1, 5 };
		assertThat( Safe.copy( ints ), is( not( sameInstance( ints ) ) ) );
		assertThat( Safe.copy( ints ), is( ints ) );
	}

	@Test
	public final void testCopyLongArray()
	{
		final long[] longs = new long[]
			{ 1, 5 };
		assertThat( Safe.copy( longs ), is( not( sameInstance( longs ) ) ) );
		assertThat( Safe.copy( longs ), is( longs ) );
	}

	@Test
	public final void testCopyFloatArray()
	{
		final float[] floats = new float[]
			{ 1, 5 };
		assertThat( Safe.copy( floats ), is( not( sameInstance( floats ) ) ) );
		assertThat( Safe.copy( floats ), is( floats ) );
	}

	@Test
	public final void testCopyDoubleArray()
	{
		final double[] doubles = new double[]
			{ 1, 5 };
		assertThat( Safe.copy( doubles ), is( not( sameInstance( doubles ) ) ) );
		assertThat( Safe.copy( doubles ), is( doubles ) );
	}

	@Test
	public final void testCopyObjectArray()
	{
		final String[] strings = new String[]
			{ "A", "B" };
		assertThat( Safe.copy( strings ), is( not( sameInstance( strings ) ) ) );
		assertThat( Safe.copy( strings ), is( strings ) );
	}

}
