/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


import java.util.Random;


/**
 * Generate random alpha-numeric characters (a-z, 0-9)
 */
public class RandomAlphaNumericSource implements Source< Character >
{

	private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789".toCharArray();

	private final Random random = new Random();
	private final char[] chars;

	/**
	 * Create a source using the supplied characters.
	 *
	 * @param chars
	 *            characters to returns
	 */
	public RandomAlphaNumericSource( final char[] chars )
	{
		this.chars = chars;
	}

	/**
	 * Create a source using the supplied characters.
	 *
	 * @param string
	 *            characters to returns
	 */
	public RandomAlphaNumericSource( final String string )
	{
		chars = string.toCharArray();
	}

	/**
	 * Create a source using the predefined characters.
	 */
	public RandomAlphaNumericSource()
	{
		this( CHARS );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#next()
	 */
	@Override
	public Character next()
	{
		return chars[random.nextInt( chars.length )];
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#reset()
	 */
	@Override
	public void reset()
	{
		// do nothing
	}

}
