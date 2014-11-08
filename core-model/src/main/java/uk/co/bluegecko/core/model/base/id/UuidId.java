/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.id;


import java.util.UUID;

import uk.co.bluegecko.core.model.id.SimpleId;


/**
 * Implementation of {@link SimpleId} backed by a UUID.
 */
public class UuidId extends AbstractSimpleId< UUID >
{

	private static final long serialVersionUID = 8392351544390130699L;

	/**
	 * Construct a new id using the supplied UUID.
	 *
	 * @param value
	 *            underlying id
	 */
	protected UuidId( final UUID value )
	{
		super( value );
	}

}
