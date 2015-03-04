/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * Core Configuration class
 */
@Configuration
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration
{

	/**
	 * Default constructor
	 */
	public SecurityConfiguration()
	{
		super();
	}

}
