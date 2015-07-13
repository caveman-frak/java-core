package uk.co.bluegecko.core.server.resource;


import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.bluegecko.core.aspect.profile.Profiled;
import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.service.HealthService;


@SuppressWarnings( "javadoc" )
@Provider
public abstract class HealthResource
{

	private final Logger logger;
	private final HealthService healthService;

	public HealthResource( final HealthService healthService )
	{
		logger = LoggerFactory.getLogger( getClass() );
		this.healthService = healthService;
	}

	@Profiled
	public Health health()
	{
		logger.info( "getting health..." );

		return healthService.getSystemHealth();
	}

	public void garbageCollect()
	{
		logger.info( "calling garbage collection..." );

		healthService.garbageCollect();
	}

}
