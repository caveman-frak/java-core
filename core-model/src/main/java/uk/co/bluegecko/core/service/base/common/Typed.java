package uk.co.bluegecko.core.service.base.common;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @param <T>
 *            generic type
 */
public abstract class Typed< T >
{

	protected Type getType( final int index )
	{
		return ( ( ParameterizedType ) getClass().getGenericSuperclass() ).getActualTypeArguments()[index];
	}

}
