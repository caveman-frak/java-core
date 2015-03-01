/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


/**
 * Generate characters using the text from Lorem Ipsum
 *
 * @see <a href="http://www.lipsum.com/">www.lipsum.com</a>
 */
public class LoremIpsumSource implements Source< Character >
{

	private static final Predicate< Character > paragraphBoundary = ( final Character ch ) -> ch == '\r' || ch == '\n';
	private static final Predicate< Character > sentenceBoundary = ( final Character ch ) -> paragraphBoundary
			.test( ch ) || ch == '.' || ch == '!' || ch == '?';
	private static final Predicate< Character > wordBoundary = ( final Character ch ) -> Character.isWhitespace( ch );

	private final URL resource;
	private Reader text;

	/**
	 * Create a character source using the text from Lorem Ipsum
	 *
	 * @see <a href="http://www.lipsum.com/">www.lipsum.com</a>
	 */
	public LoremIpsumSource()
	{
		super();

		resource = getClass().getResource( "lorem-ipsum.txt" );
		reset();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#next()
	 */
	@Override
	public Character next()
	{
		int read;
		try
		{
			read = text.read();
			if ( read == -1 )
			{
				reset();
			}
			return ( char ) read;
		}
		catch ( final IOException ex )
		{
			throw new IllegalStateException( ex );
		}
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#reset()
	 */
	@Override
	public void reset()
	{
		try
		{
			final InputStream stream = resource.openStream();
			text = new BufferedReader( new InputStreamReader( stream ) );
		}
		catch ( final IOException ex )
		{
			throw new IllegalStateException( ex );
		}
	}

	/**
	 * Generate a string of text, containing 'count' words from Lorem Ipsum.
	 *
	 * @param count
	 *            the number of words
	 * @return the text
	 */
	public String words( final int count )
	{
		return read( count, wordBoundary );
	}

	/**
	 * Generate a string of text, containing 'count' sentences from Lorem Ipsum.
	 *
	 * @param count
	 *            the number of sentences
	 * @return the text
	 */
	public String sentences( final int count )
	{
		return read( count, sentenceBoundary );
	}

	/**
	 * Generate a string of text, containing 'count' paragraphs from Lorem Ipsum.
	 *
	 * @param count
	 *            the number of paragraphs
	 * @return the text
	 */
	public String paragraphs( final int count )
	{
		return read( count, paragraphBoundary );
	}

	/**
	 * @param count
	 *            number of list items to get
	 * @param words
	 *            number of words per item
	 * @return list of words
	 */
	public List< String > list( final int count, final int words )
	{
		final List< String > list = new ArrayList<>();
		for ( int i = 0; i < count; i++ )
		{
			list.add( words( words ) );
			paragraphs( 1 );
		}
		return list;
	}

	protected String read( final int count, final Predicate< Character > delimitTest )
	{
		final StringBuilder buffer = new StringBuilder();
		int i = 0;
		while ( i < count )
		{
			final char ch = next();
			if ( delimitTest.test( ch ) )
			{
				i++;
			}
			buffer.append( ch );
		}
		buffer.deleteCharAt( buffer.length() - 1 );
		return buffer.toString();
	}

}
