/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.id;

import java.io.Serializable;


/**
 * Used to provide identity to a data elements.
 *
 * @param <T>
 *            type of Id
 */
public interface Id< T > extends Serializable, Comparable< T >
{
	// marker interface
}
