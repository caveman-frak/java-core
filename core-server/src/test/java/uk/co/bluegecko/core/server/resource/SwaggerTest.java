package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.config.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.GC;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.INFO;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.PATH;
import io.swagger.models.Swagger;
import io.swagger.parser.Swagger20Parser;

import java.io.IOException;
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

import uk.co.bluegecko.core.server.test.AbstractWebTest;


@SuppressWarnings( "javadoc" )
public class SwaggerTest extends AbstractWebTest
{

	private WebTarget target;

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		target = ClientBuilder.newClient().target( new URL( "http", "localhost", getHttpPort(), BASE_PATH ).toURI() );
	}

	@Test
	public void fetchSwaggerAsJson() throws IOException
	{
		final Invocation.Builder builder = target.path( "swagger.json" ).request( MediaType.APPLICATION_JSON_TYPE );
		final Response response = builder.get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( ( String ) response.getHeaders().getFirst( HttpHeaders.CONTENT_TYPE ),
				startsWith( MediaType.APPLICATION_JSON ) );

		final String entity = response.readEntity( String.class );

		final Swagger20Parser parser = new Swagger20Parser();
		final Swagger swagger = parser.parse( entity );
		assertThat( swagger.getSwagger(), is( "2.0" ) );
		assertThat( swagger.getPaths(), hasKey( PATH + INFO ) );
		assertThat( swagger.getPaths(), hasKey( PATH + GC ) );
	}

}
