package uk.co.bluegecko.core.service.base.common;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public abstract class Invoker< T > extends Typed< T > implements InvocationHandler
{

	/**
	 * @return proxy for T
	 */
	@SuppressWarnings( "unchecked" )
	public T proxy()
	{
		final Class< T > type = ( Class< T > ) getType( 0 );

		final Class< ? >[] interfaces = new Class< ? >[]
			{ type };
		return ( T ) Proxy.newProxyInstance( type.getClassLoader(), interfaces, this );
	}

}