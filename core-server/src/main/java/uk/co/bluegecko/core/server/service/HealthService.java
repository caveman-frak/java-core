package uk.co.bluegecko.core.server.service;


import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.server.model.Health;


@Service
public interface HealthService extends uk.co.bluegecko.core.service.Service
{

	public Health getSystemHealth();

	public void garbageCollect();

}
