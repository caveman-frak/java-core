/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


/**
 * Test for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource}
 */
public class LoremIpsumSourceTest
{

	private LoremIpsumSource source;

	/**
	 * Create the source for testing
	 */
	@Before
	public final void setUp()
	{
		source = new LoremIpsumSource();
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#LoremIpsumSource()}.
	 */
	@Test
	public final void testCtor()
	{
		assertThat( source, is( not( nullValue() ) ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#next()}.
	 */
	@Test
	public final void testNext()
	{
		assertThat( source.next(), is( 'L' ) );
		assertThat( source.next(), is( 'o' ) );
		assertThat( source.next(), is( 'r' ) );
		assertThat( source.next(), is( 'e' ) );
		assertThat( source.next(), is( 'm' ) );
		assertThat( source.next(), is( ' ' ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#reset()}.
	 */
	@Test
	public final void testReset()
	{
		assertThat( source.next(), is( 'L' ) );
		source.reset();
		assertThat( source.next(), is( 'L' ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#words(int)}.
	 */
	@Test
	public final void testWords()
	{
		assertThat( source.words( 2 ), is( "Lorem ipsum" ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#sentences(int)}.
	 */
	@Test
	public final void testSentences()
	{
		assertThat( source.sentences( 2 ),
				allOf( startsWith( "Lorem ipsum" ), containsString( " elit. Nulla " ), endsWith( "lacinia" ) ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#paragraphs(int)}.
	 */
	@Test
	public final void testParagraphs()
	{
		assertThat(
				source.paragraphs( 2 ),
				allOf( startsWith( "Lorem ipsum" ), containsString( "eget eros." ), containsString( "Donec " ),
						endsWith( " erat volutpat." ) ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#list(int, int)}.
	 */
	@SuppressWarnings( "unchecked" )
	@Test
	public final void testList()
	{
		assertThat( source.list( 2, 3 ), contains( is( "Lorem ipsum dolor" ), is( "Donec sit amet" ) ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#paragraphs(int)}.
	 */
	@Test
	public final void testParagraphsFromList()
	{
		source = new LoremIpsumSource( "lorem-list.txt" );
		assertThat(
				source.paragraphs( 2 ),
				allOf( startsWith( "Lorem ipsum" ), containsString( " adipiscing elit." ),
						containsString( "In eleifend " ), endsWith( " hendrerit tempus." ) ) );
	}

	/**
	 * Test method for {@link uk.co.bluegecko.core.test.source.LoremIpsumSource#list(int, int)}.
	 */
	@SuppressWarnings( "unchecked" )
	@Test
	public final void testListFromList()
	{
		source = new LoremIpsumSource( "lorem-list.txt" );
		assertThat( source.list( 2, 3 ), contains( is( "Lorem ipsum dolor" ), is( "In eleifend sapien" ) ) );
	}

}
