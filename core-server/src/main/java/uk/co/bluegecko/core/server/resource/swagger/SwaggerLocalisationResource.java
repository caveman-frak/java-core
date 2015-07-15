package uk.co.bluegecko.core.server.resource.swagger;


import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.BUNDLE;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.BUNDLE_NAME;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.LOCALE;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.MESSAGE;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.MESSAGE_KEY;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.PARAMETERS;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.PATH;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Location.TAG;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.server.resource.LocalisationResource;
import uk.co.bluegecko.core.service.common.LocalisationService;


@SuppressWarnings( "javadoc" )
@Singleton
@Api( tags = TAG )
@Path( PATH )
public class SwaggerLocalisationResource extends LocalisationResource
{

	@Autowired
	public SwaggerLocalisationResource( final LocalisationService localisationService )
	{
		super( localisationService );
	}

	@Override
	@ApiOperation( nickname = "bundle", value = "Localised Message Bundle", tags = TAG,
			notes = "Retrieve a localised message bundle", response = Map.class )
	@ApiResponses(
		{ @ApiResponse( code = 200, message = "Localised Message Bundle", response = Map.class ),
				@ApiResponse( code = 204, message = "Missing Bundle" ),
				@ApiResponse( code = 500, message = "Internal Error" ) } )
	@GET
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	@Path( BUNDLE )
	public Response bundle( @Context final HttpHeaders headers, @QueryParam( LOCALE ) final Locale locale,
			@PathParam( BUNDLE_NAME ) final String bundleName )
	{
		return super.bundle( headers, locale, bundleName );
	}

	@Override
	@ApiOperation( nickname = "message", value = "Localised Message", tags = TAG,
			notes = "Retrieve a localised message", response = String.class )
	@ApiResponses(
		{ @ApiResponse( code = 200, message = "Localised Message", response = String.class ),
				@ApiResponse( code = 500, message = "Internal Error" ) } )
	@GET
	@Produces(
		{ MediaType.TEXT_PLAIN } )
	@Path( MESSAGE )
	public Response message( @Context final HttpHeaders headers, @QueryParam( LOCALE ) final Locale locale,
			@PathParam( BUNDLE_NAME ) final String bundleName, @PathParam( MESSAGE_KEY ) final String messageKey,
			@MatrixParam( PARAMETERS ) final List< String > parameters )
	{
		return super.message( headers, locale, bundleName, messageKey, parameters );
	}

}
