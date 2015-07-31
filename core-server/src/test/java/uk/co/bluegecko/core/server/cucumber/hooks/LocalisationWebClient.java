package uk.co.bluegecko.core.server.cucumber.hooks;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.BUNDLE_NAME;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.MESSAGE;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.MESSAGE_KEY;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.PATH;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Locale;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@SuppressWarnings( "javadoc" )
@Component
@Scope( "cucumber-glue" )
public class LocalisationWebClient extends WebClient
{

	private static final String BUNDLE_UNDER_TEST = "i18n.common";

	public String request( final Locale locale, final String key ) throws MalformedURLException, URISyntaxException
	{
		final Response response = getTarget().path( PATH + MESSAGE ).resolveTemplate( BUNDLE_NAME, BUNDLE_UNDER_TEST )
				.resolveTemplate( MESSAGE_KEY, key ).request( MediaType.TEXT_PLAIN ).acceptLanguage( locale ).get();

		assertThat( response.getStatus(), is( Status.OK.getStatusCode() ) );

		return response.readEntity( String.class );
	}

}
