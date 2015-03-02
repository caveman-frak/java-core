/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.router;


import static org.junit.Assert.fail;

import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.service.base.router.RoutingPath;


/**
 *
 */
@Component
@RoutingPath( path = RouteFoo2.PATH )
public class RouteFoo2 implements RouteFoo
{

	/**
	 * routing path for Foo2
	 */
	public static final String PATH = "foo2";

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#count(java.lang.CharSequence)
	 */
	@Override
	public int count( final String path, final CharSequence sequence )
	{
		return sequence.length();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#bar()
	 */
	@Override
	public void bar()
	{
		fail( "bar()" );
	}

}
