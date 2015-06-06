package uk.co.bluegecko.core.server.service.base;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import uk.co.bluegecko.core.aspect.profile.Profiled;
import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.service.HealthService;
import uk.co.bluegecko.core.service.base.AbstractService;
import uk.co.bluegecko.core.service.common.LocaleService;


@SuppressWarnings( "javadoc" )
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
		final Health health = new Health( "Jersey: Up and Running!" );
		health.setOperatingSystem( getOperatingSystemDetails() );
		health.setHeapMemory( memoryMXBean.getHeapMemoryUsage() );
		health.setNonHeapMemory( memoryMXBean.getNonHeapMemoryUsage() );
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
