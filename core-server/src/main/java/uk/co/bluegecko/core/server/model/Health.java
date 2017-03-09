package uk.co.bluegecko.core.server.model;


import java.lang.management.MemoryUsage;


public interface Health
{

	public void setSystemLoadAverage( final String systemLoadAverage );

	public String getSystemLoadAverage();

	public void setNonHeapMemory( final MemoryUsage nonHeap );

	public Memory getNonHeapMemory();

	public void setHeapMemory( final MemoryUsage heap );

	public Memory getHeapMemory();

	public void setOperatingSystem( final String operatingSystem );

	public String getOperatingSystem();

	public void setArchitecture( final String architecture );

	public String getArchitecture();

	public String getStatus();

}
