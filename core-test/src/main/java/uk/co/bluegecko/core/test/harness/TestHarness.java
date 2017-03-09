/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.harness;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import ch.qos.cal10n.verifier.Cal10nError;
import ch.qos.cal10n.verifier.IMessageKeyVerifier;
import ch.qos.cal10n.verifier.MessageKeyVerifier;


/**
 * Base Test class
 */
@Configuration
@TestExecutionListeners( listeners =
	{ DependencyInjectionTestExecutionListener.class } )
@ContextConfiguration( inheritInitializers = true, inheritLocations = true, locations =
	{ "classpath:test-context.xml" } )
@PropertySource( name = "unit-test", ignoreResourceNotFound = true, encoding = "UTF-8", value =
	{ "classpath:unit-test.properties" } )
public abstract class TestHarness implements ApplicationContextAware
{

	public static final int YEAR = 2010;
	public static final Month MONTH = Month.JUNE;
	public static final int DAY = 14;

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	private ApplicationContext applicationContext;

	/**
	 * Default constructor
	 */
	public TestHarness()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext( final ApplicationContext applicationContext ) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	protected ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	public static Instant getInstant()
	{
		return LocalDateTime.of( getDate(), getTime() )
				.toInstant( getTimeZone() );
	}

	public static LocalTime getTime()
	{
		return LocalTime.NOON;
	}

	public static LocalDate getDate()
	{
		return LocalDate.of( YEAR, MONTH, DAY );
	}

	public static ZoneOffset getTimeZone()
	{
		return ZoneOffset.UTC;
	}

	public static Clock getFixedClock()
	{
		return Clock.fixed( getInstant(), getTimeZone() );
	}

	protected void verifyLocalisation( final Class< ? extends Enum< ? > > klass )
	{
		final IMessageKeyVerifier verifier = new MessageKeyVerifier( klass );
		final List< Cal10nError > errorList = verifier.verifyAllLocales();
		for ( final Cal10nError error : errorList )
		{
			System.err.println( error );
		}
		assertThat( errorList, is( empty() ) );
	}

	protected void debug( final String... text )
	{
		if ( isDebugEnabled() )
		{
			for ( final String s : text )
			{
				System.out.print( s );
			}
			System.out.println();
		}
	}

	protected Boolean isDebugEnabled()
	{
		return applicationContext.getEnvironment()
				.getProperty( "unit-test.debug", Boolean.class, false );
	}

}
