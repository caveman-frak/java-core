package uk.co.bluegecko.core.server.resource;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.service.HealthService;


@SuppressWarnings( "javadoc" )
@Api( tags = "health" )
@Path( "/health" )
public class SwaggerHealthResource extends HealthResource
{

	@Autowired
	public SwaggerHealthResource( final HealthService healthService )
	{
		super( healthService );
	}

	@Override
	@ApiOperation( nickname = "health", value = "Retreive system health information",
			notes = "Returns information on architecture, operating system, and memory / processor usage",
			response = Health.class )
	@ApiResponses(
		{ @ApiResponse( code = 200, message = "System Health" ), @ApiResponse( code = 500, message = "Internal Error" ) } )
	@GET
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	@Path( "/" )
	public Health health()
	{
		return super.health();
	}

	@Override
	@ApiOperation( nickname = "gc", value = "Trigger garbage collection",
			notes = "Triggers garbage collection on the server" )
	@ApiResponses(
		{ @ApiResponse( code = 204, message = "GC invoked" ), @ApiResponse( code = 500, message = "Internal Error" ) } )
	@POST
	@Path( "/gc" )
	public void garbageCollect()
	{
		super.garbageCollect();
	}

}