/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service;


import org.springframework.context.ApplicationContext;


/**
 * Marker interface for services.
 */
public interface Service
{

	/**
	 * Access method for the built in {@link ApplicationContext}
	 *
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext();

}
