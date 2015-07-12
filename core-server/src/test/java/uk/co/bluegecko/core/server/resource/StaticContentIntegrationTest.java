package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.ServerConstants.PATH;
import static uk.co.bluegecko.core.server.TestServerConstants.PORT;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
public class StaticContentIntegrationTest
{

	private WebTarget target;

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		target = ClientBuilder.newClient().target( new URL( "http", "localhost", PORT, PATH ).toURI() );
	}

	@Test
	public void indexPageAsHtml()
	{
		final Invocation.Builder builder = target.path( "index.html" ).request( MediaType.TEXT_HTML_TYPE );
		final Response response = builder.get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( ( String ) response.getHeaders().getFirst( HttpHeaders.CONTENT_TYPE ),
				startsWith( MediaType.TEXT_HTML ) );

		final String entity = response.readEntity( String.class );

		System.out.println( entity );
	}

}
