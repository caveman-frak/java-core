package uk.co.bluegecko.core.server.resource.swagger;


import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.GC;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.INFO;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.PATH;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.TAG;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.co.bluegecko.core.server.exceptions.WebErrorStatus;
import uk.co.bluegecko.core.server.model.base.BaseHealth;
import uk.co.bluegecko.core.server.resource.HealthResource;
import uk.co.bluegecko.core.server.service.HealthService;


@SuppressWarnings( "javadoc" )
@Singleton
@Api( tags = TAG )
@Path( PATH )
public class SwaggerHealthResource extends HealthResource
{

	@Autowired
	public SwaggerHealthResource( final HealthService healthService )
	{
		super( healthService );
	}

	@Override
	@ApiOperation( nickname = "health", value = "Retreive system health information", tags = TAG,
			notes = "Returns information on architecture, operating system, and memory / processor usage",
			response = BaseHealth.class )
	@ApiResponses(
		{ @ApiResponse( code = 200, message = "System Health", response = BaseHealth.class ),
				@ApiResponse( code = 500, message = "Internal Error" ) })
	@GET
	@Path( INFO )
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response health()
	{
		return super.health();
	}

	@Override
	@ApiOperation( nickname = "gc", value = "Trigger garbage collection", tags = TAG,
			notes = "Triggers garbage collection on the server" )
	@ApiResponses(
		{ @ApiResponse( code = 204, message = "GC invoked" ), @ApiResponse( code = 500, message = "Internal Error" ) })
	@POST
	@Path( GC )
	public void garbageCollect()
	{
		super.garbageCollect();
	}

	@ApiOperation( nickname = "exec", value = "Throws an exception", tags = TAG,
			notes = "Throws and exception, with status code", response = BaseHealth.class )
	@ApiResponses(
		{ @ApiResponse( code = 200, message = "System Health", response = BaseHealth.class ),
				@ApiResponse( code = 400, message = "Bad Request" ),
				@ApiResponse( code = 500, message = "Internal Error" ) })
	@GET
	@Path( "/exec/{status}" )
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Response health( @PathParam( "status" ) final int status)
	{
		throw new FakeException( Status.fromStatusCode( status ) );
	}

	protected static class FakeException extends RuntimeException implements WebErrorStatus
	{

		private static final long serialVersionUID = 1L;

		private final Status status;

		public FakeException( final Status status )
		{
			this.status = status;
		}

		@Override
		public Status getStatus()
		{
			return status;
		}

	}

}
