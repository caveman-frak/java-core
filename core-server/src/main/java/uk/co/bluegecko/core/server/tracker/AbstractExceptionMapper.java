package uk.co.bluegecko.core.server.tracker;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.MDC;
import org.slf4j.cal10n.LocLogger;

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

	/**
	 * Default constructor, takes logger argument.
	 *
	 * @param logger
	 *            logger to use
	 */
	protected AbstractExceptionMapper( final LocLogger logger )
	{
		super();

		this.logger = logger;
	}

	@Override
	public Response toResponse( final E exception )
	{
		final Status status = getStatus( exception );
		final Object entity = getEntity( exception );

		if ( logger.isInfoEnabled() )
		{
			logger.info( Log.ERROR, exception.getClass().getSimpleName(), status, entity );
			logger.info( "exception", exception );
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
