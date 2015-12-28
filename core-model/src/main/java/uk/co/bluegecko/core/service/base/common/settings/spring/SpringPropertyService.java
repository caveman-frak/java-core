/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.settings.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.model.key.Defaulted;
import uk.co.bluegecko.core.service.common.settings.PropertyService;


/**
 * Spring {@link PropertySource} implementation of PropertyService
 */
@Service
public class SpringPropertyService implements PropertyService
{

	private final Environment environment;

	/**
	 * Default constructor referencing the spring {@link Environment}
	 *
	 * @param environment
	 *            the underlying environment
	 */
	@Autowired
	public SpringPropertyService( final Environment environment )
	{
		this.environment = environment;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.settings.PropertyService#getProperty(java.lang.Enum)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T extends Enum< ? > > String property( final T key )
	{
		final String value = environment.getProperty( key.name() );
		if ( value == null && key instanceof Defaulted )
		{
			final Defaulted< String > defaultedKey = (uk.co.bluegecko.core.model.key.Defaulted< String > ) key;
			return defaultedKey.defaultValue();
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.settings.PropertyService#getProperty(java.lang.Enum, java.lang.Object)
	 */
	@Override
	public < T extends Enum< ? > > String property( final T key, final String fallback )
	{
		return has( key ) ? property( key ) : fallback;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.settings.PropertyService#hasProperty(java.lang.Enum)
	 */
	@Override
	public < T extends Enum< ? > > boolean has( final T key )
	{
		return environment.containsProperty( key.name() );
	}

}
