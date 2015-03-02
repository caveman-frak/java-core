/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.provider;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.service.base.provider.NotProvidedException;
import uk.co.bluegecko.core.service.base.provider.Provider;
import uk.co.bluegecko.core.test.harness.TestHarness;


/**
 *
 */
public class ProviderTest extends TestHarness
{

	private static final String TEST = "TEST";

	@Autowired
	private Provider< ProviderFoo > fooProvider;

	/**
	 * Test bean autowiring
	 */
	@Test
	public final void testAutowiring()
	{
		assertThat( fooProvider.collection(), hasSize( 2 ) );
	}

	/**
	 * Test invoking count (supported by Foo1)
	 */
	@Test
	public final void testInvokeCount()
	{
		assertThat( fooProvider.proxy().count( TEST ), is( 4 ) );
	}

	/**
	 * Test invoking count (supported by Foo2)
	 */
	@Test
	public final void testInvokeChars()
	{
		assertThat( fooProvider.proxy().chars( TEST ), is( new char[]
				{ 'T', 'E', 'S', 'T' } ) );
	}

	/**
	 * Test invoking count (not supported)
	 */
	@Test( expected = NotProvidedException.class )
	public final void testInvokeBar()
	{
		fooProvider.proxy().bar();
		fail();
	}

}
