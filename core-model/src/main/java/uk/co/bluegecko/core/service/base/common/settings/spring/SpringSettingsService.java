/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.settings.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.model.Defaulted;
import uk.co.bluegecko.core.model.TypedKey;
import uk.co.bluegecko.core.service.common.settings.SettingsService;


/**
 * Spring {@link PropertySource} implementation of SettingsService
 */
@Service
public class SpringSettingsService implements SettingsService
{

	private final Environment environment;

	/**
	 * Default constructor referencing the spring {@link Environment}
	 *
	 * @param environment
	 *            the underlying environment
	 */
	@Autowired
	public SpringSettingsService( final Environment environment )
	{
		this.environment = environment;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * uk.co.bluegecko.core.service.common.settings.SettingsService#getSetting(uk.co.bluegecko.core.service.common.settings
	 * .Setting)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < E > E getSetting( final TypedKey< E > setting )
	{
		final E value = environment.getProperty( setting.name(), setting.type() );
		if ( value == null && setting instanceof Defaulted )
			return ( (uk.co.bluegecko.core.model.Defaulted< E > ) setting ).defaultValue();
		else
			return value;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * uk.co.bluegecko.core.service.common.settings.SettingsService#getSetting(uk.co.bluegecko.core.service.common.settings
	 * .Setting, java.lang.Object)
	 */
	@Override
	public < E > E getSetting( final TypedKey< E > setting, final E fallback )
	{
		return environment.getProperty( setting.name(), setting.type(), fallback );
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * uk.co.bluegecko.core.service.common.settings.SettingsService#hasSetting(uk.co.bluegecko.core.service.common.settings
	 * .Setting)
	 */
	@Override
	public boolean hasSetting( final TypedKey< ? > setting )
	{
		return environment.containsProperty( setting.name() );
	}

}
