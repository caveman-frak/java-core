/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.provider;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Base class for delegation based provider model.
 *
 * @param <T>
 *            type of class
 *
 */
public abstract class Provider< T > implements InvocationHandler
{

	private final List< T > implementations;

	/**
	 * @param implementations
	 *            list of providers
	 */
	@Autowired( required = false )
	protected Provider( final List< T > implementations )
	{
		super();

		this.implementations = implementations;
	}

	/**
	 * Default constructor in case no providers found
	 */
	protected Provider()
	{
		this( Collections.emptyList() );
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke( final Object proxy, final Method method, final Object[] args )
	{
		for ( final T impl : implementations )
		{
			try
			{
				return method.invoke( impl, args );
			}
			catch ( final InvocationTargetException ex )
			{
				if ( !( ex.getCause() instanceof NotProvidedException ) )
					throw new IllegalStateException( ex );
			}
			catch ( IllegalAccessException | IllegalArgumentException ex )
			{
				throw new IllegalStateException( ex );
			}
		}
		throw new NotProvidedException();
	}

	protected Collection< T > collection()
	{
		return Collections.unmodifiableList( implementations );
	}

	/**
	 * @return proxy for T
	 */
	@SuppressWarnings( "unchecked" )
	public T proxy()
	{
		final Class< T > type = ( Class< T > ) ( ( ParameterizedType ) getClass().getGenericSuperclass() )
				.getActualTypeArguments()[0];

		return ( T ) Proxy.newProxyInstance( type.getClassLoader(), new Class< ? >[]
				{ type }, this );
	}

}
