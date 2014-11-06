/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.settings.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.service.common.settings.Defaulted;
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
	public String getProperty( final Enum< ? > property )
	{
		final String value = environment.getProperty( property.name() );
		if ( value == null && property instanceof Defaulted )
			return ( ( Defaulted< String > ) property ).defaultValue();
		else
			return value;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.settings.PropertyService#getProperty(java.lang.Enum, java.lang.Object)
	 */
	@Override
	public String getProperty( final Enum< ? > property, final String fallback )
	{
		return hasProperty( property ) ? getProperty( property ) : fallback;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.service.common.settings.PropertyService#hasProperty(java.lang.Enum)
	 */
	@Override
	public boolean hasProperty( final Enum< ? > property )
	{
		return environment.containsProperty( property.name() );
	}

}
