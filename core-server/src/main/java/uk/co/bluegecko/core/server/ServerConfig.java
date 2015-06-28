package uk.co.bluegecko.core.server;


import io.swagger.jersey.listing.ApiListingResourceJSON;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import uk.co.bluegecko.core.server.filter.TrackerTokenFilter;
import uk.co.bluegecko.core.server.resources.HealthResource;


@SuppressWarnings( "javadoc" )
@Configuration
@ApplicationPath( "/api" )
public class ServerConfig extends ResourceConfig
{

	public ServerConfig()
	{
		register( HealthResource.class );
		register( TrackerTokenFilter.class );
		register( JacksonFeature.class );
		register( ApiListingResourceJSON.class );
	}

}
