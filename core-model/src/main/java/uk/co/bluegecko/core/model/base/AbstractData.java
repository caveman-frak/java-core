/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base;


import java.io.Serializable;

import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.model.Data;


/**
 * Default base implementation of {@link Data} interface.
 * <p>
 * {@link Object#equals(Object)}, {@link Object#hashCode()}, {@link Comparable#compareTo(Object)} defer to the Id.
 *
 * @param <K>
 *            type of id
 */
public class AbstractData< K extends Serializable & Comparable< K >> implements Data< K >
{

	private static final long serialVersionUID = 2192649767237869981L;

	private final K key;

	/**
	 * Default constructor.
	 *
	 * @param id
	 *            new id value
	 */
	public AbstractData( final K id )
	{
		super();

		this.key = id;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.Data#getId()
	 */
	@Override
	public final K getKey()
	{
		return key;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public final int compareTo( final Data< K > that )
	{
		return getKey().compareTo( that.getKey() );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode()
	{
		return getKey().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals( final Object obj )
	{
		final EqualsBuilder< Data< ? > > builder = new EqualsBuilder<>( this, obj );
		if ( builder.isResolved() )
		{
			return builder.isSame();
		}
		return getKey().equals( builder.getRhs().getKey() );
	}

}
