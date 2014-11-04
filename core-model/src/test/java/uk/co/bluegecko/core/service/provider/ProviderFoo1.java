/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.provider;


import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.service.provider.NotProvidedException;


/**
 *
 */
@Component
public class ProviderFoo1 implements ProviderFoo
{

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#count(java.lang.CharSequence)
	 */
	@Override
	public int count( final CharSequence sequence )
	{
		return sequence.length();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#chars(java.lang.CharSequence)
	 */
	@Override
	public char[] chars( final CharSequence sequence )
	{
		throw new NotProvidedException();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#bar()
	 */
	@Override
	public void bar()
	{
		throw new NotProvidedException();
	}

}
