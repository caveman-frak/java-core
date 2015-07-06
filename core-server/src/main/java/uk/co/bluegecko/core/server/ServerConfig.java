package uk.co.bluegecko.core.server;


import static uk.co.bluegecko.core.server.ServerConstants.PATH;
import io.swagger.jersey.listing.ApiListingResourceJSON;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@SuppressWarnings( "javadoc" )
@Configuration
@ApplicationPath( PATH )
public class ServerConfig extends ResourceConfig
{

	public ServerConfig()
	{
		register( JacksonFeature.class );
		register( ApiListingResourceJSON.class );

		packages( "uk.co.bluegecko.core.server" );
		setApplicationName( "Example Server" );
	}

}