package uk.co.bluegecko.core.server.service;


import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.server.model.Health;


@SuppressWarnings( "javadoc" )
@Service
public interface HealthService
{

	public Health getSystemHealth();

	public void garbageCollect();

}
