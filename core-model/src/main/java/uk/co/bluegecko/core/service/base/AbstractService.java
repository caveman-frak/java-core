/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;
import org.springframework.context.ApplicationContext;

import uk.co.bluegecko.core.service.Service;
import uk.co.bluegecko.core.service.common.LocaleService;
import ch.qos.cal10n.MessageConveyor;


/**
 * Abstract implementation of {@link Service} with support for {@link Logger} and {@link ApplicationContext}
 */
public abstract class AbstractService implements Service
{

	private final ApplicationContext applicationContext;
	private final LocaleService localeService;
	private final LocLogger logger;

	/**
	 * Create an abstract service base with {@link LocLogger} and {@link LocaleService} support.
	 *
	 * @param applicationContext
	 *            the application context to use
	 * @param localeService
	 *            the locale service to use
	 */
	protected AbstractService( final ApplicationContext applicationContext, final LocaleService localeService )
	{
		this.applicationContext = applicationContext;
		this.localeService = localeService;
		logger = buildLocLogger( localeService.system(), getLoggerName() );
	}

	/**
	 * Create a new localised logger
	 *
	 * @param locale
	 *            locale to use when localising log message
	 * @param logName
	 *            name of the logger
	 * @return
	 */
	private LocLogger buildLocLogger( final Locale locale, final String logName )
	{
		return new LocLoggerFactory( new MessageConveyor( locale ) ).getLocLogger( logName );
	}

	/**
	 * Return the name to use for the logger using the class name.
	 *
	 * @param klass
	 *            use the class name as the logger name
	 * @return the name of the logger
	 */
	protected String getLoggerName( final Class< ? > klass )
	{
		return klass.getName();
	}

	/**
	 * Return the name to use for the service logger, defaults to class name, override to change.
	 *
	 * @return the name of the logger
	 */
	protected String getLoggerName()
	{
		return getLoggerName( getClass() );
	}

	/**
	 * Access method for the built in {@link LocLogger}.
	 *
	 * @return the logger
	 */
	protected LocLogger getLogger()
	{
		return logger;
	}

	/**
	 * Access method for the built in {@link LocaleService}.
	 *
	 * @return the locale service
	 */
	protected LocaleService getLocaleService()
	{
		return localeService;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.Service#getApplicationContext()
	 */
	@Override
	public ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

}
