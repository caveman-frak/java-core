package uk.co.bluegecko.core.server.model;


@SuppressWarnings( "javadoc" )
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

	public void setHeapMemory( final Memory heap )
	{
		this.heap = heap;
	}

	public Memory getNonHeapMemory()
	{
		return nonHeap;
	}

	public void setNonHeapMemory( final Memory nonHeap )
	{
		this.nonHeap = nonHeap;
	}

}
