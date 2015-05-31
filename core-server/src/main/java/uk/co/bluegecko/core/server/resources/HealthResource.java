package uk.co.bluegecko.core.server.resources;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.server.model.Health;


@SuppressWarnings( "javadoc" )
@Component
@Path( "/health" )
public class HealthResource
{

	@GET
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Health health()
	{
		return new Health( "Jersey: Up and Running!" );
	}

}
