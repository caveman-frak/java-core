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

import uk.co.bluegecko.core.server.model.Health;


@SuppressWarnings( "javadoc" )
@Api( value = "/health", produces = "application/json,application/xml", description = "System health", tags = "health" )
public interface SwaggerHealthResource
{

	@ApiOperation( value = "Retreive system health information",
			notes = "Returns information on architecture, operating system, and memory / processor usage",
			response = Health.class )
	@ApiResponses( value =
		{ @ApiResponse( code = 200, message = "System Health info" ) } )
	@GET
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	@Path( "" )
	public Health health();

	@ApiOperation( value = "Trigger garbage collection" )
	@POST
	@Path( "gc" )
	public void garbageCollect();

}