package uk.co.bluegecko.core.test.matcher.logging;


import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcher.info;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcher.warn;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import ch.qos.cal10n.MessageConveyor;


@SuppressWarnings( "javadoc" )
public class LoggingEventMatcherTest
{

	@BaseName( "uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcherTest$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		TEST1, TEST2
	}
	private LocLogger logger;

	@Before
	public final void setUp()
	{
		final MessageConveyor messageConveyor = new MessageConveyor( Locale.ENGLISH );
		final LocLoggerFactory loggerFactory = new LocLoggerFactory( messageConveyor );
		logger = loggerFactory.getLocLogger( LoggingEventMatcherTest.class );
	}

	@Test
	public void testLogInfoWithParams()
	{
		final TestLogger testLogger = TestLoggerFactory.getTestLogger( LoggingEventMatcherTest.class );

		logger.info( Log.TEST1, 1, "One" );

		assertThat( testLogger.getLoggingEvents(), hasItem( info( Log.TEST1, 1, "One" ) ) );
	}

	@Test
	public void testLogWarnWithoutParams()
	{
		final TestLogger testLogger = TestLoggerFactory.getTestLogger( LoggingEventMatcherTest.class );

		logger.warn( Log.TEST2 );

		assertThat( testLogger.getLoggingEvents(), hasItem( warn( Log.TEST2 ) ) );
	}

}
