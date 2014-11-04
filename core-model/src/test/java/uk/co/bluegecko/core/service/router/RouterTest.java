/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.router;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.test.harness.TestHarness;


/**
 *
 */
public class RouterTest extends TestHarness
{

	private static final String TEST = "TEST";

	private Router< RouteFoo > fooRouter;

	/**
	 * get a Router from the application context
	 */
	@Before
	public final void setUp()
	{
		fooRouter = getApplicationContext().getBean( Router.class );
	}

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
		assertThat( fooRouter.proxy().count( RouteFoo1.PATH, TEST ), is( 4 ) );
	}

	/**
	 * Test invoking count forward to Foo1
	 */
	@Test
	public final void testInvokeCountFoo2()
	{
		assertThat( fooRouter.proxy().count( RouteFoo2.PATH, TEST ), is( 4 ) );
	}

	/**
	 * Test invoking count forward to Foo3 (non-existant path)
	 */
	@Test( expected = NotRoutableException.class )
	public final void testInvokeCountFoo3()
	{
		fooRouter.proxy().count( "foo3", TEST );
		fail( "testInvokeCountFoo3()" );
	}

	/**
	 * Test invoking count (not forwardable)
	 */
	@Test( expected = NotRoutableException.class )
	public final void testInvokeBar()
	{
		fooRouter.proxy().bar();
		fail( "testInvokeBar()" );
	}

}
