/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.id;


/**
 * Id wrapper for compound identifiers.
 *
 * @param <P>
 *            the type of underlying primary identifier
 * @param <S>
 *            the type of underlying secondary identifier
 */
public interface CompoundId< P extends Comparable< P >, S extends Comparable< S > > extends Id< CompoundId< P, S >>
{

	/**
	 * @return the underlying primary identifier
	 */
	public P getPrimary();

	/**
	 * @return the underlying secondary identifier
	 */
	public S getSecondary();

}
