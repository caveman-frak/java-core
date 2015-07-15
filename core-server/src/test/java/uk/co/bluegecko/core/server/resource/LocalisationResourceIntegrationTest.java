package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.TestServerConstants.PORT;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.BUNDLE;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.BUNDLE_NAME;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.LOCALE;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.MESSAGE;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.MESSAGE_KEY;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.PARAMETERS;
import static uk.co.bluegecko.core.server.resource.WebResourceConstants.Location.PATH;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uk.co.bluegecko.core.Application;


@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = Application.class )
@WebAppConfiguration
@IntegrationTest( "server.port=" + PORT )
public class LocalisationResourceIntegrationTest
{

	private WebTarget target;

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		final HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic( "test", "test123" );
		final Configuration configuration = new ClientConfig().register( authenticationFeature );
		target = ClientBuilder.newClient( configuration ).target(
				new URL( "http", "localhost", PORT, BASE_PATH ).toURI() );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void fetchBundle()
	{
		final Response response = target.path( PATH + BUNDLE ).resolveTemplate( BUNDLE_NAME, "i18n.test" )
				.request( MediaType.APPLICATION_JSON_TYPE ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( ( String ) response.getHeaders().getFirst( HttpHeaders.CONTENT_TYPE ),
				startsWith( MediaType.APPLICATION_JSON ) );

		assertThat( ( Map< String, String > ) response.readEntity( Map.class ), hasEntry( "blue", "Blue" ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void fetchBundleForQueryFR()
	{
		final Response response = target.path( PATH + BUNDLE ).resolveTemplate( BUNDLE_NAME, "i18n.test" )
				.queryParam( LOCALE, "fr" ).request( MediaType.APPLICATION_JSON_TYPE ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( ( Map< String, String > ) response.readEntity( Map.class ), hasEntry( "blue", "Bleu" ) );
	}

	@Test
	public void fetchBundleMissing()
	{
		final Response response = target.path( PATH + BUNDLE ).resolveTemplate( BUNDLE_NAME, "XXXX" )
				.request( MediaType.APPLICATION_JSON_TYPE ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( ( Set< ? > ) response.readEntity( Map.class ).keySet(), empty() );
	}

	@Test
	public void fetchMessage()
	{
		final Response response = target.path( PATH + MESSAGE ).resolveTemplate( BUNDLE_NAME, "i18n.test" )
				.resolveTemplate( MESSAGE_KEY, "blue" ).request( MediaType.TEXT_PLAIN ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( ( String ) response.getHeaders().getFirst( HttpHeaders.CONTENT_TYPE ),
				startsWith( MediaType.TEXT_PLAIN ) );

		assertThat( response.readEntity( String.class ), is( "Blue" ) );
	}

	@Test
	public void fetchMessageForHeaderFR()
	{
		final Response response = target.path( PATH + MESSAGE ).resolveTemplate( BUNDLE_NAME, "i18n.test" )
				.resolveTemplate( MESSAGE_KEY, "blue" ).request( MediaType.TEXT_PLAIN ).acceptLanguage( Locale.FRENCH )
				.get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.readEntity( String.class ), is( "Bleu" ) );
	}

	@Test
	public void fetchMessageMissing()
	{
		final Response response = target.path( PATH + MESSAGE ).resolveTemplate( BUNDLE_NAME, "i18n.test" )
				.resolveTemplate( MESSAGE_KEY, "XXXX" ).request( MediaType.TEXT_PLAIN ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.readEntity( String.class ), is( "**XXXX**" ) );
	}

	@Test
	public void fetchMessageWithParams()
	{
		final Response response = target.path( PATH + MESSAGE ).resolveTemplate( BUNDLE_NAME, "i18n.test" )
				.resolveTemplate( MESSAGE_KEY, "greeting" ).matrixParam( PARAMETERS, "Bob" )
				.request( MediaType.TEXT_PLAIN ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.readEntity( String.class ), is( "Hello Bob!" ) );
	}

}
