/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.id;


import uk.co.bluegecko.core.model.id.SimpleId;


/**
 * Implementation of {@link SimpleId} backed by a Long.
 */
public class LongId extends AbstractSimpleId< Long >
{

	private static final long serialVersionUID = 8392351544390130699L;

	/**
	 * Construct a new id using the supplied long.
	 *
	 * @param value
	 *            underlying id
	 */
	protected LongId( final long value )
	{
		super( value );
	}

}
