/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.locale;


import java.util.Locale;

import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.service.common.LocaleService;


/**
 *
 */
@Service
public class DefaultLocaleService implements LocaleService
{

	/**
	 *
	 */
	public DefaultLocaleService()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocaleService#getSystemLocale()
	 */
	@Override
	public Locale getSystemLocale()
	{
		return Locale.getDefault();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocaleService#getUserLocale()
	 */
	@Override
	public Locale getUserLocale()
	{
		return Locale.getDefault();
	}

}
