/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


/**
 * An extension of model that understands identity.
 *
 * @param <K>
 *            the type of id
 */
public interface Data< K extends Comparable< K >> extends Model, Comparable< Data< K >>
{

	/**
	 * Return the id for the data entry.
	 *
	 * @return the id
	 */
	public K getId();

}
