package uk.co.bluegecko.core.aspect.profile;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import uk.co.bluegecko.core.config.TestContextLoader;
import uk.co.bluegecko.core.test.harness.TestHarness;
import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

import com.google.common.collect.ImmutableList;


@SuppressWarnings( "javadoc" )
@ContextConfiguration( inheritLocations = true, loader = TestContextLoader.class )
public class ProfileAspectTest extends TestHarness
{

	@Autowired
	private Foo foo;
	private TestLogger testLogger;

	@Before
	public void setUp()
	{
		testLogger = TestLoggerFactory.getTestLogger( ProfileAspect.class );
	}

	@Test
	public void test()
	{
		assertThat( testLogger.isInfoEnabled(), is( true ) );

		foo.bar();

		final ImmutableList< LoggingEvent > events = testLogger.getLoggingEvents();
		assertThat( events, is( not( empty() ) ) );
	}

}
