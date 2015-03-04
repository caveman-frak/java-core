/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;


/**
 * Core Configuration class
 */
@Configuration
@EnableCaching
@EnableLoadTimeWeaving
public class CoreConfiguration
{

	/**
	 * Default constructor
	 */
	public CoreConfiguration()
	{
		super();
	}

}
