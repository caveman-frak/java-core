package uk.co.bluegecko.core.server.model;


import java.lang.management.MemoryUsage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings( "javadoc" )
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Health
{

	private String status;
	private String operatingSystem;
	private Memory heap;
	private Memory nonHeap;

	public Health()
	{
		super();
	}

	public Health( final String status )
	{
		this();

		this.status = status;
	}

	public String getStatus()
	{
		return status;
	}

	public String getOperatingSystem()
	{
		return operatingSystem;
	}

	public void setOperatingSystem( final String operatingSystem )
	{
		this.operatingSystem = operatingSystem;
	}

	public Memory getHeapMemory()
	{
		return heap;
	}

	public void setHeapMemory( final MemoryUsage heap )
	{
		this.heap = new Memory( heap );
	}

	public Memory getNonHeapMemory()
	{
		return nonHeap;
	}

	public void setNonHeapMemory( final MemoryUsage nonHeap )
	{
		this.nonHeap = new Memory( nonHeap );
	}

}
