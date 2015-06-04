package uk.co.bluegecko.core.server.resources;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.aspect.profile.Profiled;
import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.service.HealthService;


@SuppressWarnings( "javadoc" )
@Service
@Path( "/health" )
public class HealthResource
{

	private final Logger logger;

	private final HealthService healthService;

	@Autowired
	public HealthResource( final HealthService healthService )
	{
		logger = LoggerFactory.getLogger( getClass() );
		this.healthService = healthService;
	}

	@Profiled
	@GET
	@Produces(
		{ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
	public Health health()
	{
		logger.info( "getting health..." );

		return healthService.getSystemHealth();
	}

	@POST
	@Path( "gc" )
	public void garbageCollect()
	{
		healthService.garbageCollect();
	}

}
