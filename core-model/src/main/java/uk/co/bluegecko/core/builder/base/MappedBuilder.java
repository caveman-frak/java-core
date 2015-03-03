/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.builder.base;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import uk.co.bluegecko.core.builder.Builder;


/**
 * Base {@link Builder}, backed by a {@link Map} to hold fields until the build step.
 *
 * @param <T>
 *            the type to be built
 * @param <B>
 *            the builder class (reference to self)
 */
public abstract class MappedBuilder< T, B extends Builder< T, B >> implements Builder< T, B >
{

	private final Map< String, Object > fields;

	protected MappedBuilder( final Map< String, Object > fields )
	{
		super();

		this.fields = fields;
	}

	protected MappedBuilder()
	{
		this( new HashMap<>() );
	}

	/**
	 * Copy constructor, used by {@link MappedBuilder#copy()} method
	 *
	 * @param builder
	 *            the builder to copy
	 */
	protected MappedBuilder( final MappedBuilder< T, B > builder )
	{
		this( new HashMap<>( builder.fields ) );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.builder.Builder#reset()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public B reset()
	{
		fields.clear();

		return ( B ) this;
	}

	@SuppressWarnings( "unchecked" )
	protected B with( final String key, final Object value )
	{
		fields.put( key, value );

		return ( B ) this;
	}

	protected Map< String, Object > fields()
	{
		return Collections.unmodifiableMap( fields );
	}

	protected Object get( final String name )
	{
		return fields.get( name );
	}

}
