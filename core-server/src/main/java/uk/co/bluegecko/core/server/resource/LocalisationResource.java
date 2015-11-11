package uk.co.bluegecko.core.server.resource;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import uk.co.bluegecko.core.service.common.LocalisationService;


@SuppressWarnings( "javadoc" )
@Provider
public abstract class LocalisationResource
{

	private final LocalisationService localisationService;

	public LocalisationResource( final LocalisationService localisationService )
	{
		this.localisationService = localisationService;
	}

	public Response bundle( final HttpHeaders headers, final Locale locale, final String bundleName )
	{
		final Map< String, Object > messages = localisationService.getMessages( getLocale( headers, locale ),
				bundleName );
		return messages.isEmpty() ? Response.noContent().build() : Response.ok( messages ).build();
	}

	public Response message( final HttpHeaders headers, final Locale locale, final String bundleName,
			final String messageKey, final List< String > parameters )
	{
		final String message = localisationService.getMessage( getLocale( headers, locale ), bundleName, messageKey,
				parameters.toArray() );
		return Response.ok( message, MediaType.TEXT_PLAIN ).build();
	}

	protected Locale getLocale( final HttpHeaders headers, final Locale locale )
	{
		if ( locale != null )
		{
			return locale;
		}

		final List< Locale > acceptableLanguages = headers.getAcceptableLanguages();
		final Locale firstLocale = acceptableLanguages.get( 0 );
		if ( !firstLocale.getLanguage().equals( "*" ) )
		{
			return firstLocale;
		}
		return null;
	}

}
