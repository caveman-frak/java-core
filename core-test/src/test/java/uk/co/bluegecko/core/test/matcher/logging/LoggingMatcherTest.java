package uk.co.bluegecko.core.test.matcher.logging;


import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcher.info;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcher.warn;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingMessageMatcher.message;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import uk.org.lidalia.slf4jtest.LoggingEvent;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import ch.qos.cal10n.MessageConveyor;


@SuppressWarnings( "javadoc" )
public class LoggingMatcherTest
{

	@BaseName( "uk.co.bluegecko.core.test.matcher.logging.LoggingMatcherTest$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		TEST1, TEST2
	}

	private LocLogger logger;
	private TestLogger testLogger;

	@Before
	public final void setUp()
	{
		final MessageConveyor messageConveyor = new MessageConveyor( Locale.ENGLISH );
		final LocLoggerFactory loggerFactory = new LocLoggerFactory( messageConveyor );
		logger = loggerFactory.getLocLogger( LoggingMatcherTest.class );

		testLogger = TestLoggerFactory.getTestLogger( LoggingMatcherTest.class );
	}

	@Test
	public void testLogInfoWithParams()
	{
		logger.info( Log.TEST1, 1, "One" );

		final List< LoggingEvent > loggingEvents = testLogger.getLoggingEvents();
		assertThat( loggingEvents, hasItem( info( Log.TEST1, 1, "One" ) ) );
		assertThat( loggingEvents, hasItem( message( "Message with params #1 1 and #2 \"One\"" ) ) );
	}

	@Test
	public void testLogWarnWithoutParams()
	{
		logger.warn( Log.TEST2 );

		final List< LoggingEvent > loggingEvents = testLogger.getLoggingEvents();
		assertThat( loggingEvents, hasItem( warn( Log.TEST2 ) ) );
		assertThat( loggingEvents, hasItem( message( "Message with no params" ) ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testLogMultipleMessages()
	{
		logger.info( Log.TEST1, 1, "One" );
		logger.warn( Log.TEST2 );

		assertThat( testLogger.getLoggingEvents(), hasItems( info( Log.TEST1, 1, "One" ), warn( Log.TEST2 ) ) );
	}

}
