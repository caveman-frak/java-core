/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.router;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Base class for delegation based provider model.
 *
 * @param <T>
 *            type of class
 *
 */
public abstract class Router< T > implements InvocationHandler
{

	private final Map< String, T > routings;

	/**
	 * @param implementations
	 *            list of providers
	 */
	@Autowired( required = false )
	protected Router( final List< T > implementations )
	{
		super();

		this.routings = new HashMap<>();
		for ( final T impl : implementations )
		{
			if ( impl instanceof Routable )
			{
				routings.put( ( ( Routable ) impl ).path(), impl );
			}
			else
			{
				final RoutingPath annotation = impl.getClass().getAnnotation( RoutingPath.class );
				if ( annotation != null )
				{
					routings.put( annotation.path(), impl );
				}
			}
		}
	}

	/**
	 * Default constructor in case no providers found
	 */
	protected Router()
	{
		this( Collections.emptyList() );
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke( final Object proxy, final Method method, final Object[] args )
	{
		if ( method.getParameterCount() > 0 )
		{
			String path = null;
			final Annotation[][] annotations = method.getParameterAnnotations();
			for ( int i = 0; i < annotations.length; i++ )
			{
				if ( method.getParameters()[i].getType().equals( String.class ) )
				{
					for ( final Annotation annotation : annotations[i] )
					{
						if ( annotation.annotationType().equals( RoutingPath.class ) )
						{
							path = ( String ) args[i];
							break;
						}
					}
				}
				if ( path != null )
				{
					break;
				}
			}
			if ( path != null )
			{
				final T forward = forward( path );
				if ( forward != null )
				{
					try
					{
						return method.invoke( forward, args );
					}
					catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException ex )
					{
						throw new IllegalStateException( ex );
					}
				}
			}
		}
		throw new NotRoutableException();
	}

	protected T forward( final String path )
	{
		return routings.get( path );
	}

	protected Collection< T > collection()
	{
		return Collections.unmodifiableCollection( routings.values() );
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
