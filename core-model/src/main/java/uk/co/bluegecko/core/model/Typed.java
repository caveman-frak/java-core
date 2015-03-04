/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


/**
 * A lookup key with a type.
 *
 * @param <E>
 *            type of value and default
 */
public interface Typed< E >
{

	/**
	 * The type of the value for the setting.
	 *
	 * @return the type of the value
	 */
	public Class< E > type();

}
