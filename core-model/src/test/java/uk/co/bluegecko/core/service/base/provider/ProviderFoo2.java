/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.provider;


import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.service.base.provider.NotProvidedException;


/**
 *
 */
@Component
public class ProviderFoo2 implements ProviderFoo
{

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#count(java.lang.CharSequence)
	 */
	@Override
	public int count( final CharSequence sequence )
	{
		throw new NotProvidedException();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.scratchpad.provider.Foo#chars(java.lang.CharSequence)
	 */
	@Override
	public char[] chars( final CharSequence sequence )
	{
		return new StringBuilder( sequence ).toString().toCharArray();
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
