/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


/**
 * Generate sequential integers
 */
public class NumericSequenceSource implements Source< Integer >
{

	private final int start;
	private int next;

	/**
	 * Use specified start.
	 * 
	 * @param start
	 *            starting integer
	 */
	public NumericSequenceSource( final int start )
	{
		this.start = start;

		reset();
	}

	/**
	 * Use start of 1.
	 */
	public NumericSequenceSource()
	{
		this( 1 );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.source.Source#next()
	 */
	@Override
	public Integer next()
	{
		return next++;
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
