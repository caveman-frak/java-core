/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.io;


import java.io.InputStream;

import uk.co.bluegecko.core.test.source.Source;


/**
 * InputStream that will generate a fixed quantity of random character (7bit) data.
 *
 * @author tpickard
 *
 */
public class RandomInputStream extends InputStream
{

	private final Source< Character > source;
	private final int size;
	private int read;

	/**
	 * Construct an RandomInputStream.
	 *
	 * @param size
	 *            quantity of data to generate
	 * @param source
	 *            character generator
	 */
	public RandomInputStream( final int size, final Source< Character > source )
	{
		super();

		this.source = source;
		this.size = size;
		read = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#read()
	 */
	@Override
	public int read()
	{
		if ( read++ < size )
			return generateData();
		return -1;
	}

	protected int generateData()
	{
		return source.next();
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.InputStream#available()
	 */
	@Override
	public int available()
	{
		return size - read;
	}

	@Override
	public void close()
	{
		source.reset();
	}

}
