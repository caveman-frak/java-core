package uk.co.bluegecko.core.server.tracker;


import java.util.Locale;

import javax.ws.rs.ext.Provider;

import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.MessageConveyor;


/**
 * Basic exception mapper.
 */
@Provider
public final class TrackerExceptionMapper extends AbstractExceptionMapper< Throwable >
{

	/**
	 * No -arg constructor.
	 */
	public TrackerExceptionMapper()
	{
		super( new LocLoggerFactory( new MessageConveyor( Locale.ENGLISH ) )
				.getLocLogger( TrackerExceptionMapper.class ) );
	}

}
