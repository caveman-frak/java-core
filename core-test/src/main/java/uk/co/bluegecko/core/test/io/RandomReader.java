/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.io;


import java.io.IOException;
import java.io.Reader;

import uk.co.bluegecko.core.test.source.Source;


/**
 * Reader that will generate a fixed quantity of random character (7bit) data.
 *
 * @author tpickard
 *
 */
public class RandomReader extends Reader
{

	private final Source< Character > source;
	private final int size;
	private int read;

	/**
	 * Construct an RandomReader.
	 *
	 * @param size
	 *            quantity of data to generate
	 * @param source
	 *            character generator
	 *
	 */
	public RandomReader( final int size, final Source< Character > source )
	{
		super();

		this.source = source;
		this.size = size;
		read = 0;
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#read(char[], int, int)
	 */
	@Override
	public int read( final char[] cbuf, final int off, final int len )
	{
		int i;
		for ( i = 0; i < len; i++ )
		{
			if ( read++ < size )
			{
				cbuf[i + off] = generateData();
			}
			else
			{
				break;
			}
		}
		return i == 0 ? -1 : i;
	}

	protected char generateData()
	{
		return source.next();
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Reader#close()
	 */
	@Override
	public void close() throws IOException
	{
		source.reset();
	}

}
