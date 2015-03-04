/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.id;


import uk.co.bluegecko.core.model.id.SimpleId;


/**
 * Implementation of {@link SimpleId} backed by a String.
 */
public class StringId extends AbstractSimpleId< String >
{

	private static final long serialVersionUID = 8392351544390130699L;

	/**
	 * Construct a new id using the supplied String.
	 *
	 * @param value
	 *            underlying id
	 */
	protected StringId( final String value )
	{
		super( value );
	}

}
