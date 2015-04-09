/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


/**
 * Interface to denote transient error conditions that may be retried.
 *
 * Usually used for unexpected errors.
 */
public interface RecoverableError
{

	/**
	 * Return the number of failed attempts.
	 * 
	 * @return the number of failed attempts
	 */
	public short getFailedCount();

}
