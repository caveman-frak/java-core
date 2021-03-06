/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.id;


/**
 * Id wrapper for a single type.
 *
 * @param <T>
 *            the type of underlying identifier
 */
public interface SimpleId< T extends Comparable< T > > extends Identifier< SimpleId< T >>
{

	/**
	 * @return the underlying identifier
	 */
	public T value();

}
