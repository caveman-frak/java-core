/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.source;


/**
 * Act as a source for elements of type T
 *
 * @param <T>
 *            type of element to generate
 *
 */
public interface Source< T >
{

	/**
	 * Return the next generated element
	 *
	 * @return the next available element
	 */
	public T next();

	/**
	 * Reset the source to its initial state (optional)
	 */
	public void reset();

}
