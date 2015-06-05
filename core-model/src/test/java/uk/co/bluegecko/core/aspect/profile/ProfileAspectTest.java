package uk.co.bluegecko.core.aspect.profile;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.caught;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import uk.co.bluegecko.core.aspect.Foo;
import uk.co.bluegecko.core.config.TestContextLoader;
import uk.co.bluegecko.core.test.harness.TestHarness;
import uk.org.lidalia.slf4jext.Level;
import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import com.google.common.collect.ImmutableList;


@SuppressWarnings( "javadoc" )
@ContextConfiguration( inheritLocations = true, loader = TestContextLoader.class )
public class ProfileAspectTest extends TestHarness
{

	@Autowired
	@Qualifier( "profile" )
	private Foo foo;

	private TestLogger testLogger;

	@Before
	public void setUp()
	{
		testLogger = TestLoggerFactory.getTestLogger( ProfileAspect.class );
	}

	@After
	public void tearDown()
	{
		testLogger.clear();
	}

	@Test
	public void testClean()
	{
		assertThat( testLogger.isDebugEnabled(), is( true ) );

		foo.bar( false );

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );
		assertThat( events, hasSize( 1 ) );
		final LoggingEvent event = events.get( 0 );
		assertThat( event.getLevel(), is( Level.DEBUG ) );
		assertThat( event.getMessage(), startsWith( "Method \"FooBase.bar(...)\" took " ) );
	}

	@Test
	public void testDirty()
	{
		assertThat( testLogger.isDebugEnabled(), is( true ) );

		capture( ( ) -> foo.bar( true ) );
		assertThat( caught(), is( instanceOf( IllegalStateException.class ) ) );

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );
		assertThat( events, hasSize( 1 ) );
		final LoggingEvent event = events.get( 0 );
		assertThat( event.getLevel(), is( Level.DEBUG ) );
		assertThat( event.getMessage(), startsWith( "Method \"FooBase.bar(...)\" took " ) );
	}

}
