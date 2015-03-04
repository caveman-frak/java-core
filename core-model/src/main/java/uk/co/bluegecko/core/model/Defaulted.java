/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


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
