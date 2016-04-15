package uk.co.bluegecko.core.server.tracker;


import javax.ws.rs.ext.Provider;


/**
 * Basic exception mapper.
 */
@Provider
public final class TrackerExceptionMapper extends AbstractExceptionMapper< Throwable >
{

	/**
	 * No -arg constructor.
	 *
	 * Ensure exception logging is turned on.
	 */
	public TrackerExceptionMapper()
	{
		this( true );
	}

	protected TrackerExceptionMapper( final boolean logException )
	{
		super( TrackerExceptionMapper.class, logException );
	}

}
