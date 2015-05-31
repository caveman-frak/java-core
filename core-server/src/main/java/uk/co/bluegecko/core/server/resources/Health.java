package uk.co.bluegecko.core.server.resources;


@SuppressWarnings( "javadoc" )
public class Health
{

	private String status;

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
}
