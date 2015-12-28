package uk.co.bluegecko.core.model.id;


import java.io.Serializable;


/**
 * @author tim
 *         Interface indicating the item has an id property.
 * @param <K>
 *            the type of id
 */
public interface Identified< K extends Serializable & Comparable< K > >
{

	/**
	 * Return the id for the data entry.
	 *
	 * @return the id
	 */
	public K id();

}
