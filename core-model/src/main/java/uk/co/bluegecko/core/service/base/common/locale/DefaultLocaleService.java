/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.locale;


import java.util.Locale;

import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.service.common.LocaleService;


/**
 * Simple implementation of {@link LocaleService} using {@link Locale#getDefault()}
 */
@Service
public class DefaultLocaleService implements LocaleService
{

	/**
	 * Default zero arg constructor
	 */
	public DefaultLocaleService()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocaleService#getSystemLocale()
	 */
	@Override
	public Locale system()
	{
		return Locale.ENGLISH;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.LocaleService#getUserLocale()
	 */
	@Override
	public Locale user()
	{
		return Locale.getDefault();
	}

}
