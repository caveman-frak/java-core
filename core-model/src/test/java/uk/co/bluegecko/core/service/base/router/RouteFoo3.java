/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.router;


import static org.junit.Assert.fail;

import org.springframework.stereotype.Component;


/**
 *
 */
@Component
public class RouteFoo3 implements RouteFoo
{

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
