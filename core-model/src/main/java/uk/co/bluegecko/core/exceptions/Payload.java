/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.exceptions;


/**
 * Marker interface for Exception with an additional payload.
 * 
 * @param <T>
 *            the type of payload
 *
 */
public interface Payload< T >
{

	/**
	 * Return the payload.
	 * 
	 * @return the payload
	 */
	public T getPayload();

}
