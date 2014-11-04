/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.router;


import static org.junit.Assert.fail;

import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.service.router.Routable;


/**
 *
 */
@Component
public class RouteFoo1 implements RouteFoo, Routable
{

	/**
	 * routing path for Foo1
	 */
	public static final String PATH = "foo1";

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

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.router.Routable#path()
	 */
	@Override
	public String path()
	{
		return PATH;
	}

}
