package uk.co.bluegecko.core.service.base;


import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcher.error;

import java.util.Locale;

import org.junit.Test;
import org.slf4j.cal10n.LocLogger;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.service.base.FooServiceImpl.Foo;
import uk.co.bluegecko.core.test.harness.TestHarness;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;


@SuppressWarnings( "javadoc" )
public class AbstractServiceTest extends TestHarness
{

	@Autowired
	private FooService service;

	@Test
	public void testAutowire()
	{
		assertThat( service, is( instanceOf( FooServiceImpl.class ) ) );
	}

	@Test
	public void testGetLoggerName()
	{
		assertThat( getService().getLoggerName(), is( FooServiceImpl.class.getName() ) );
	}

	@Test
	public void testGetLogger()
	{
		assertThat( getService().getLogger(), is( instanceOf( LocLogger.class ) ) );
	}

	@Test
	public void testGetLocaleService()
	{
		assertThat( getService().getLocaleService().getSystemLocale(), is( Locale.ENGLISH ) );
	}

	@Test
	public void testGetApplicationContext()
	{
		assertThat( service.getApplicationContext(), is( getApplicationContext() ) );
	}

	@Test
	public void testBar()
	{
		final TestLogger testLogger = TestLoggerFactory.getTestLogger( FooServiceImpl.class );

		service.bar();

		assertThat( testLogger.getLoggingEvents(), hasItem( error( Foo.BAR ) ) );
	}

	private FooServiceImpl getService()
	{
		return ( FooServiceImpl ) service;
	}

}
