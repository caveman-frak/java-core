package uk.co.bluegecko.core.server.service.base;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.aspect.profile.Profiled;
import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.model.Memory;
import uk.co.bluegecko.core.server.service.HealthService;


@SuppressWarnings( "javadoc" )
@Service
@Lazy
public class HealthServiceBase implements HealthService
{

	private final OperatingSystemMXBean operatingSystemMXBean;

	private final MemoryMXBean memoryMXBean;

	public HealthServiceBase()
	{
		operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		memoryMXBean = ManagementFactory.getMemoryMXBean();
	}

	@Profiled
	@Override
	public Health getSystemHealth()
	{
		final Health health = new Health( "Jersey: Up and Running!" );
		health.setOperatingSystem( getOperatingSystemDetails() );
		health.setHeapMemory( new Memory( memoryMXBean.getHeapMemoryUsage() ) );
		health.setNonHeapMemory( new Memory( memoryMXBean.getNonHeapMemoryUsage() ) );
		return health;
	}

	protected String getOperatingSystemDetails()
	{
		return operatingSystemMXBean.getArch() + " / " + operatingSystemMXBean.getName() + " / "
				+ operatingSystemMXBean.getVersion();
	}

	@Override
	public void garbageCollect()
	{
		memoryMXBean.gc();
	}

}