package uk.co.bluegecko.core.server.resource;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.bluegecko.core.aspect.profile.Profiled;
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
	public Response health()
	{
		logger.info( "getting health..." );

		return Response.ok( healthService.getSystemHealth() ).build();
	}

	public void garbageCollect()
	{
		logger.info( "calling garbage collection..." );

		healthService.garbageCollect();
	}

}
