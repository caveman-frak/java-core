/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;


/**
 * Core Configuration class
 */
@Configuration
@EnableLoadTimeWeaving( aspectjWeaving = AspectJWeaving.ENABLED )
@EnableSpringConfigured
@EnableAspectJAutoProxy
@ComponentScan( basePackages = "uk.co.bluegecko.core" )
public class CoreConfiguration
{

	/**
	 * Default constructor
	 */
	public CoreConfiguration()
	{
		super();
	}

	/**
	 * Define the load time weaver to use for ApsectJ
	 *
	 * @return the LTW
	 * @throws Throwable
	 *             if the LTW can not be found or instantiated
	 */
	@Bean
	public InstrumentationLoadTimeWeaver loadTimeWeaver() throws Throwable
	{
		final InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		return loadTimeWeaver;
	}

}
