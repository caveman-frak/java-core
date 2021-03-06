/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.router;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.test.harness.TestHarness;


public class RouterTest extends TestHarness
{

	private static final String TEST = "TEST";

	@Autowired
	private Router< RouteFoo > fooRouter;

	/**
	 * Test bean autowiring
	 */
	@Test
	public final void testAutowiring()
	{
		assertThat( fooRouter.collection(), hasSize( 2 ) );
	}

	/**
	 * Test invoking count forward to Foo1
	 */
	@Test
	public final void testInvokeCountFoo1()
	{
		assertThat( fooRouter.proxy()
				.count( RouteFoo1.PATH, TEST ), is( 4 ) );
	}

	/**
	 * Test invoking count forward to Foo1
	 */
	@Test
	public final void testInvokeCountFoo2()
	{
		assertThat( fooRouter.proxy()
				.count( RouteFoo2.PATH, TEST ), is( 4 ) );
	}

	/**
	 * Test invoking count forward to Foo3 (non-existant path)
	 */
	@Test( expected = NotRoutableException.class )
	public final void testInvokeCountFoo3()
	{
		fooRouter.proxy()
				.count( "foo3", TEST );
		fail( "testInvokeCountFoo3()" );
	}

	/**
	 * Test invoking count (not forwardable)
	 */
	@Test( expected = NotRoutableException.class )
	public final void testInvokeBar()
	{
		fooRouter.proxy()
				.bar();
		fail( "testInvokeBar()" );
	}

}
