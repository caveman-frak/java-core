package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.BUNDLE;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.BUNDLE_NAME;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.PATH;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import uk.co.bluegecko.core.server.test.AbstractIntegrationTest;


@SuppressWarnings( "javadoc" )
public class LocalisationResourceIT extends AbstractIntegrationTest
{

	@Test
	public void fetchBundle() throws MalformedURLException, URISyntaxException
	{
		final Response response = getTarget().path( PATH + BUNDLE ).resolveTemplate( BUNDLE_NAME, "i18n.common" )
				.request( MediaType.APPLICATION_JSON_TYPE ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );
		assertThat( response.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( ( String ) response.getHeaders().getFirst( HttpHeaders.CONTENT_TYPE ),
				startsWith( MediaType.APPLICATION_JSON ) );

		assertThat( ( Map< ?, ? > ) response.readEntity( Map.class ), hasEntry( "blue", "Blue" ) );
	}

}
