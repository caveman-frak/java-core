/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base;


import org.slf4j.Logger;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;
import org.springframework.context.ApplicationContext;

import ch.qos.cal10n.MessageConveyor;

import uk.co.bluegecko.core.service.Service;
import uk.co.bluegecko.core.service.common.LocaleService;


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
		logger = new LocLoggerFactory( new MessageConveyor( localeService.getSystemLocale() ) )
				.getLocLogger( getLoggerName() );
	}

	/**
	 * Return the name to use for the logger, defaults to class name, override to change.
	 *
	 * @return the name of the logger
	 */
	protected String getLoggerName()
	{
		return getClass().getName();
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

	/**
	 * Access method for the built in {@link ApplicationContext}
	 * 
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

}
