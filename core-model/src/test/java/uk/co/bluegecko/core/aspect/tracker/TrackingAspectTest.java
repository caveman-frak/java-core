package uk.co.bluegecko.core.aspect.tracker;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.caught;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import com.google.common.collect.ImmutableList;

import uk.co.bluegecko.core.aspect.Foo;
import uk.co.bluegecko.core.config.TestContextLoader;
import uk.co.bluegecko.core.test.harness.TestHarness;
import uk.org.lidalia.slf4jext.Level;
import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;


@ContextConfiguration( inheritLocations = true, loader = TestContextLoader.class )
public class TrackingAspectTest extends TestHarness
{

	@Autowired
	@Qualifier( "tracker" )
	private Foo foo;

	@Autowired
	@Qualifier( "in-service" )
	private Foo fooInService;

	@Autowired
	@Qualifier( "is-service" )
	private Foo fooIsService;

	private TestLogger testLogger;

	@Before
	public void setUp()
	{
		testLogger = TestLoggerFactory.getTestLogger( TrackingAspect.class );
	}

	@After
	public void tearDown()
	{
		testLogger.clear();
	}

	@Test
	public void testAutowiring()
	{
		assertThat( testLogger.isDebugEnabled(), is( true ) );
		assertThat( foo, is( not( nullValue() ) ) );
		assertThat( fooInService, is( not( nullValue() ) ) );
		assertThat( fooIsService, is( not( nullValue() ) ) );
	}

	@Test
	public void testClean()
	{
		foo.bar( false );

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );
		assertThat( events, hasSize( 2 ) );
		final LoggingEvent beforeEvent = events.get( 0 );
		assertThat( beforeEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( beforeEvent.getMessage(), is( "Entering \"FooBase.bar(...)\"." ) );
		final LoggingEvent afterEvent = events.get( 1 );
		assertThat( afterEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( afterEvent.getMessage(), is( "Exiting \"FooBase.bar(...)\"." ) );
	}

	@Test
	public void testDirty()
	{
		capture( () -> foo.bar( true ) );
		assertThat( caught(), is( instanceOf( IllegalStateException.class ) ) );

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );

		assertThat( events, hasSize( 2 ) );
		final LoggingEvent beforeEvent = events.get( 0 );
		assertThat( beforeEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( beforeEvent.getMessage(), is( "Entering \"FooBase.bar(...)\"." ) );
		final LoggingEvent afterEvent = events.get( 1 );
		assertThat( afterEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( afterEvent.getMessage(),
				is( "Exiting after throwing \"IllegalStateException\" from \"FooBase.bar(...)\"." ) );
	}

	@Test
	public void testInService()
	{
		fooInService.bar( false );

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );
		assertThat( events, hasSize( 2 ) );
		final LoggingEvent beforeEvent = events.get( 0 );
		assertThat( beforeEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( beforeEvent.getMessage(), is( "Entering \"FooService.bar(...)\"." ) );
		final LoggingEvent afterEvent = events.get( 1 );
		assertThat( afterEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( afterEvent.getMessage(), is( "Exiting \"FooService.bar(...)\"." ) );
	}

	@Test
	public void testIsService()
	{
		fooIsService.bar( false );

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );
		assertThat( events, hasSize( 2 ) );
		final LoggingEvent beforeEvent = events.get( 0 );
		assertThat( beforeEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( beforeEvent.getMessage(), is( "Entering \"FooService.bar(...)\"." ) );
		final LoggingEvent afterEvent = events.get( 1 );
		assertThat( afterEvent.getLevel(), is( Level.DEBUG ) );
		assertThat( afterEvent.getMessage(), is( "Exiting \"FooService.bar(...)\"." ) );
	}

}
