package uk.co.bluegecko.core.server.cucumber.hooks;


import static uk.co.bluegecko.core.server.config.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_PASSWORD;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_USER;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;


@SuppressWarnings( "javadoc" )
public abstract class WebClient
{

	protected WebTarget getTarget() throws URISyntaxException, MalformedURLException
	{
		final HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic( TEST_USER,
				TEST_PASSWORD );
		final Configuration configuration = new ClientConfig().register( authenticationFeature );
		final Integer port = Integer.valueOf( System.getProperty( "test.http.port" ) );

		final WebTarget target = ClientBuilder.newClient( configuration ).target(
				new URL( "http", "localhost", port, BASE_PATH ).toURI() );
		return target;
	}

}
