/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base;


import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.MessageConveyor;

import uk.co.bluegecko.core.service.Service;
import uk.co.bluegecko.core.service.common.LocaleService;


/**
 *
 */
public class AbstractService implements Service
{

	private final LocaleService localeService;
	private final LocLogger logger;

	/**
	 * Create an abstract service base with {@link LocLogger} and {@link LocaleService} support.
	 *
	 * @param localeService
	 *            the locale service to use
	 */
	protected AbstractService( final LocaleService localeService )
	{
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

}
