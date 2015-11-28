package uk.co.bluegecko.core.model;


import java.io.Serializable;


/**
 * @author tim
 *         Interface indicating the item has a key property.
 * @param <K>
 *            the type of id
 */
public interface Keyed< K extends Serializable & Comparable< K >>
{

	/**
	 * Return the key for the data entry.
	 *
	 * @return the key
	 */
	public K getKey();

}
