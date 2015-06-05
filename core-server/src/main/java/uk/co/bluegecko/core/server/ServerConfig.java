package uk.co.bluegecko.core.server;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import uk.co.bluegecko.core.server.filter.TrackerTokenFilter;
import uk.co.bluegecko.core.server.resources.HealthResource;


@SuppressWarnings( "javadoc" )
@Configuration
public class ServerConfig extends ResourceConfig
{

	public ServerConfig()
	{
		register( HealthResource.class );
		register( TrackerTokenFilter.class );
	}

}
