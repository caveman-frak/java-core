/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


/**
 * Interface to denote transient error conditions that may be retried.
 *
 * Usually used for unexpected errors.
 */
public interface TransientError
{

	/**
	 * Return the number of failed attempts.
	 * 
	 * @return the number of failed attempts
	 */
	public short getFailedCount();

}
