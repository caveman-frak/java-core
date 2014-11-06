/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


/**
 * A typed lookup key with a default value.
 *
 * @param <E>
 *            type of value and default
 */
public interface Defaulted< E >
{

	/**
	 * Return the default value.
	 *
	 * @return the default value
	 */
	public E defaultValue();

}
