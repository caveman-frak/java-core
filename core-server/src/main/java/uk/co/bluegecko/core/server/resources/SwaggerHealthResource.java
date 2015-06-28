package uk.co.bluegecko.core.server.resources;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
	public Health health();

	@ApiOperation( value = "Trigger garbage collection" )
	public void garbageCollect();
}
