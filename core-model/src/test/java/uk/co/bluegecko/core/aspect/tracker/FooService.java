package uk.co.bluegecko.core.aspect.tracker;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.aspect.Foo;
import uk.co.bluegecko.core.service.Service;


@SuppressWarnings( "javadoc" )
@Component( "is-service" )
public class FooService implements Service, Foo
{

	@Override
	public ApplicationContext getApplicationContext()
	{
		return null;
	}

	@Override
	public void bar( final boolean tantrum )
	{
		if ( tantrum ) { throw new IllegalStateException(); }
	}

}
