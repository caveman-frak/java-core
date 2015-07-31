package uk.co.bluegecko.core.server.test;


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
import org.junit.Before;


@SuppressWarnings( "javadoc" )
public class AbstractIntegrationTest
{

	private WebTarget target;

	protected int getHttpPort()
	{
		return Integer.valueOf( System.getProperty( "test.http.port" ) );
	}

	protected int getJmxPort()
	{
		return Integer.valueOf( System.getProperty( "test.jmx.port" ) );
	}

	protected int getSshPort()
	{
		return Integer.valueOf( System.getProperty( "test.ssh.port" ) );
	}

	protected WebTarget getTarget()
	{
		return target;
	}

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		final HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic( TEST_USER,
				TEST_PASSWORD );
		final Configuration configuration = new ClientConfig().register( authenticationFeature );
		target = ClientBuilder.newClient( configuration ).target(
				new URL( "http", "localhost", getHttpPort(), BASE_PATH ).toURI() );
	}

}
