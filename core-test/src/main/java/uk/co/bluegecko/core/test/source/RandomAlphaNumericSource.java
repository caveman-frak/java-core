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

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#next()
	 */
	@Override
	public Character next()
	{
		return CHARS[random.nextInt( CHARS.length )];
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
