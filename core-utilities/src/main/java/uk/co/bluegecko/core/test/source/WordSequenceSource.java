/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


import static uk.co.bluegecko.core.test.data.WordBuilder.intToWords;


/**
 * Generate sequential integers
 */
public class WordSequenceSource implements Source< String >
{

	private final int start;
	private int next;

	/**
	 * Use specified start.
	 *
	 * @param start
	 *            starting integer
	 */
	public WordSequenceSource( final int start )
	{
		this.start = start;

		reset();
	}

	/**
	 * Use start of 1.
	 */
	public WordSequenceSource()
	{
		this( 1 );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#next()
	 */
	@Override
	public String next()
	{
		return intToWords( next++ );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#reset()
	 */
	@Override
	public void reset()
	{
		next = start;
	}

}
