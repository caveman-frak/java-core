package uk.co.bluegecko.core.server.service.base;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.aspect.profile.Profiled;
import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.model.base.BaseHealth;
import uk.co.bluegecko.core.server.service.HealthService;
import uk.co.bluegecko.core.service.base.AbstractService;
import uk.co.bluegecko.core.service.common.LocaleService;


@Service
@Lazy
public class HealthServiceBase extends AbstractService implements HealthService
{

	private final OperatingSystemMXBean operatingSystemMXBean;
	private final MemoryMXBean memoryMXBean;

	@Autowired
	public HealthServiceBase( final ApplicationContext applicatioContext, final LocaleService localeService )
	{
		super( applicatioContext, localeService );

		operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		memoryMXBean = ManagementFactory.getMemoryMXBean();
	}

	@Profiled
	@Override
	public Health getSystemHealth()
	{
		final Health health = new BaseHealth( "Jersey: Up and Running!" );
		health.setArchitecture( getArchitectureDetails() );
		health.setOperatingSystem( getOperatingSystemDetails() );
		health.setHeapMemory( memoryMXBean.getHeapMemoryUsage() );
		health.setNonHeapMemory( memoryMXBean.getNonHeapMemoryUsage() );
		health.setSystemLoadAverage( getSystemLoadAverage() );
		return health;
	}

	private String getSystemLoadAverage()
	{
		return NumberFormat.getPercentInstance()
				.format( operatingSystemMXBean.getSystemLoadAverage() / 100 );
	}

	private String getArchitectureDetails()
	{
		return operatingSystemMXBean.getArch() + ", " + operatingSystemMXBean.getAvailableProcessors() + " cores";
	}

	private String getOperatingSystemDetails()
	{
		return operatingSystemMXBean.getName() + " v" + operatingSystemMXBean.getVersion();
	}

	@Override
	public void garbageCollect()
	{
		memoryMXBean.gc();
	}

}
