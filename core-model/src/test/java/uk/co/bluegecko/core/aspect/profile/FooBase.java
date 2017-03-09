package uk.co.bluegecko.core.aspect.profile;


import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.aspect.Foo;


@Component( "profile" )
public class FooBase implements Foo
{

	@Override
	@Profiled
	public void bar( final boolean tantrum )
	{
		if ( tantrum )
		{
			throw new IllegalStateException();
		}
	}

}
