package uk.co.bluegecko.core.server;


import static uk.co.bluegecko.core.server.ServerConstants.PATH;
import static uk.co.bluegecko.core.server.ServerConstants.PORT;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@SuppressWarnings( "javadoc" )
@Configuration
@ApplicationPath( PATH )
@PropertySource( name = "applicationProperties", value = "application.properties", ignoreResourceNotFound = true )
public class ServerConfig extends ResourceConfig implements EnvironmentAware
{

	public ServerConfig()
	{
		// register packages
		packages( "uk.co.bluegecko.core.server" );
	}

	@Override
	public void setEnvironment( final Environment environment )
	{
		setApplicationName( environment.getProperty( "info.build.artifact", "sample-server" ) );

		// setup swagger
		swaggerConfig( environment );
	}

	protected void swaggerConfig( final Environment environment )
	{
		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle( environment.getProperty( "info.build.name", "Sample Server" ) );
		beanConfig.setDescription( environment.getProperty( "info.build.description", "This is an Sample Server" ) );
		beanConfig.setTermsOfServiceUrl( "http://www.bluegecko.co.uk/terms/" );
		beanConfig.setContact( "Caveman Frak" );
		beanConfig.setLicense( "Apache 2.0" );
		beanConfig.setLicenseUrl( "http://www.apache.org/licenses/LICENSE-2.0.html" );
		beanConfig.setHost( "red-dragon.local:" + PORT );
		beanConfig.setBasePath( PATH );
		beanConfig.setVersion( environment.getProperty( "info.build.version", "0.0.0" ) );
		beanConfig.setSchemes( new String[]
			{ "http" } );
		beanConfig.setResourcePackage( "uk.co.bluegecko.core.server.resource" );
		beanConfig.setScan( true );

		register( ApiListingResource.class );
		register( SwaggerSerializers.class );
	}

}