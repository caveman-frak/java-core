package uk.co.bluegecko.core.server.tracker;


import java.util.Locale;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.MDC;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.MessageConveyor;
import uk.co.bluegecko.core.exceptions.ClientError;
import uk.co.bluegecko.core.exceptions.Payload;
import uk.co.bluegecko.core.exceptions.ServerError;
import uk.co.bluegecko.core.server.exceptions.WebErrorStatus;


/**
 * Basic exception mapper that constructs a response based on the exception type and payload.
 *
 * @param <E>
 *            type of exception
 *
 */
public abstract class AbstractExceptionMapper< E extends Throwable > implements ExceptionMapper< E >, TrackerToken
{

	private final LocLogger logger;
	private final boolean logException;

	/**
	 * Default constructor, takes logger argument and flag to log exception. The exception logging flag is intended for
	 * use in tests to reduce excess log noise.
	 *
	 * @param logger
	 *            logger to use
	 * @param logException
	 *            should the exception be logged
	 */
	protected AbstractExceptionMapper( final LocLogger logger, final boolean logException )
	{
		super();

		this.logger = logger;
		this.logException = logException;
	}

	/**
	 * Default constructor, takes logger target argument and flag to log exception. The exception logging flag is
	 * intended for use in tests to reduce excess log noise.
	 *
	 * @param klass
	 *            class to use for logger target
	 * @param logException
	 *            should the exception be logged
	 */
	protected AbstractExceptionMapper( final Class< ? extends AbstractExceptionMapper< ? > > klass,
			final boolean logException )
	{
		this( new LocLoggerFactory( new MessageConveyor( Locale.ENGLISH ) ).getLocLogger( klass ), logException );
	}

	@Override
	public Response toResponse( final E exception )
	{
		final Status status = getStatus( exception );
		final Object entity = getEntity( exception );

		if ( logger.isInfoEnabled() )
		{
			logger.info( Log.ERROR, exception.getClass().getSimpleName(), status, entity );
			if ( logException )
			{
				logger.info( "exception", exception );
			}
		}

		return Response.status( status ).entity( entity ).type( getMediaType( entity ) ).build();
	}

	protected MediaType getMediaType( final Object entity )
	{
		return entity instanceof String ? MediaType.TEXT_PLAIN_TYPE : MediaType.APPLICATION_JSON_TYPE;
	}

	protected Status getStatus( final Throwable exception )
	{
		if ( exception instanceof WebErrorStatus )
		{
			final Status status = ( ( WebErrorStatus ) exception ).getStatus();
			return status != null ? status : Status.INTERNAL_SERVER_ERROR;
		}
		else if ( exception instanceof ClientError )
		{
			return Status.BAD_REQUEST;
		}
		else if ( exception instanceof ServerError )
		{
			return Status.INTERNAL_SERVER_ERROR;
		}
		else if ( exception.getCause() != null )
		{
			return getStatus( exception.getCause() );
		}
		return Status.INTERNAL_SERVER_ERROR;
	}

	protected Object getEntity( final Throwable exception )
	{
		if ( exception instanceof Payload )
		{
			return ( ( Payload< ? > ) exception ).payload();
		}
		else if ( exception.getCause() != null )
		{
			return getEntity( exception.getCause() );
		}
		final String token = MDC.get( TOKEN );
		final String counter = MDC.get( COUNTER );
		return token != null ? token + "/" + counter : "";
	}

}
