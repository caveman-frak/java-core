package uk.co.bluegecko.core.aspect.service;


import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.aspect.Foo;


@Component( "in-service" )
public class FooService implements Foo
{

	@Override
	public void bar( final boolean tantrum )
	{
		if ( tantrum )
		{
			throw new IllegalStateException();
		}
	}

}
