package uk.co.bluegecko.core.server.model.base;


import java.lang.management.MemoryUsage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.model.Memory;


@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class BaseHealth implements Health
{

	private String status;
	private String architecture;
	private String operatingSystem;
	private BaseMemory heap;
	private BaseMemory nonHeap;
	private String systemLoadAverage;

	public BaseHealth()
	{
		super();
	}

	public BaseHealth( final String status )
	{
		this();

		this.status = status;
	}

	@Override
	public String getStatus()
	{
		return status;
	}

	@Override
	public String getArchitecture()
	{
		return architecture;
	}

	@Override
	public void setArchitecture( final String architecture )
	{
		this.architecture = architecture;
	}

	@Override
	public String getOperatingSystem()
	{
		return operatingSystem;
	}

	@Override
	public void setOperatingSystem( final String operatingSystem )
	{
		this.operatingSystem = operatingSystem;
	}

	@Override
	public Memory getHeapMemory()
	{
		return heap;
	}

	@Override
	public void setHeapMemory( final MemoryUsage heap )
	{
		this.heap = new BaseMemory( heap );
	}

	@Override
	public Memory getNonHeapMemory()
	{
		return nonHeap;
	}

	@Override
	public void setNonHeapMemory( final MemoryUsage nonHeap )
	{
		this.nonHeap = new BaseMemory( nonHeap );
	}

	@Override
	public String getSystemLoadAverage()
	{
		return systemLoadAverage;
	}

	@Override
	public void setSystemLoadAverage( final String systemLoadAverage )
	{
		this.systemLoadAverage = systemLoadAverage;
	}

}
