package uk.co.bluegecko.core.server.resource;


import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.aspect.profile.Profiled;
import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.service.HealthService;


@SuppressWarnings( "javadoc" )
@Provider
@Path( "/health" )
public class HealthResource implements SwaggerHealthResource
{

	private final Logger logger;

	private final HealthService healthService;

	@Autowired
	public HealthResource( final HealthService healthService )
	{
		logger = LoggerFactory.getLogger( getClass() );
		this.healthService = healthService;
	}

	@Override
	@Profiled
	public Health health()
	{
		logger.info( "getting health..." );

		return healthService.getSystemHealth();
	}

	@Override
	public void garbageCollect()
	{
		healthService.garbageCollect();
	}

}
