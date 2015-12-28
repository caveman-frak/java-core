/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
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
public interface CompoundId< P extends Comparable< P >, S extends Comparable< S > > extends Identifier< CompoundId< P, S >>
{

	/**
	 * @return the underlying primary identifier
	 */
	public P primary();

	/**
	 * @return the underlying secondary identifier
	 */
	public S secondary();

}
