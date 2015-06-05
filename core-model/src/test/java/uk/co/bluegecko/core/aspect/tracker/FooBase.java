package uk.co.bluegecko.core.aspect.tracker;


import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.aspect.Foo;


@SuppressWarnings( "javadoc" )
@Component( "tracker" )
public class FooBase implements Foo
{

	@Override
	@Tracked
	public void bar( final boolean tantrum )
	{
		if ( tantrum ) { throw new IllegalStateException(); }
	}

}
