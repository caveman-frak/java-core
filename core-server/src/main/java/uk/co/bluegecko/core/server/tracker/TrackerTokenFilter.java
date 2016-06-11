package uk.co.bluegecko.core.server.tracker;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.SecureRandom;
import java.time.Clock;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.MessageConveyor;


/**
 * Add a tracker token into the logging context for incoming requests
 *
 */
@Provider
public class TrackerTokenFilter implements ContainerRequestFilter, ContainerResponseFilter, TrackerToken
{

	private static final String UNAUTHENTICATED = "??";
	private static final int TOKEN_SIZE = 12;

	private final LocLogger logger;

	/**
	 * No-arg constructor for token filter.
	 */
	public TrackerTokenFilter()
	{
		super();

		logger = new LocLoggerFactory( new MessageConveyor( Locale.ENGLISH ) ).getLocLogger( TrackerTokenFilter.class );
	}

	@Override
	public void filter( final ContainerRequestContext requestContext ) throws IOException
	{
		String token = requestContext.getHeaderString( TOKEN );
		if ( StringUtils.isEmpty( token ) )
		{
			token = createToken( TOKEN_SIZE );
		}
		MDC.put( TOKEN, token );
		MDC.put( COUNTER, createCounter( Clock.systemUTC() ) );

		final Principal userPrincipal = requestContext.getSecurityContext().getUserPrincipal();
		final String name = userPrincipal != null ? userPrincipal.getName() : UNAUTHENTICATED;
		MDC.put( USERNAME, name );

		if ( logger.isInfoEnabled() )
		{
			logger.info( Log.REQUEST, name, requestContext.getMethod(), requestContext.getUriInfo().getRequestUri() );
		}
	}

	@Override
	public void filter( final ContainerRequestContext requestContext, final ContainerResponseContext responseContext )
			throws IOException
	{
		final String token = MDC.get( TOKEN );
		if ( StringUtils.isNotEmpty( token ) )
		{
			responseContext.getHeaders().add( TOKEN, token );
		}

		if ( logger.isInfoEnabled() )
		{
			logger.info( Log.RESPONSE, requestContext.getMethod(), requestContext.getUriInfo().getRequestUri(),
					elapsed( Clock.systemUTC() ) );
		}
	}

	protected Long elapsed( final Clock clock )
	{
		final String counter = MDC.get( COUNTER );
		final Long elapsed = StringUtils.isNotEmpty( counter ) ? clock.millis() - decodeCounter( counter ) : 0;
		return elapsed;
	}

	/**
	 * Create and encode a new tracker token.
	 *
	 * @param bytes
	 *            number of bytes to encode
	 *
	 * @return tracker token
	 * @throws UnsupportedEncodingException
	 *             if unable to encode the bytes
	 */
	protected String createToken( final int bytes ) throws UnsupportedEncodingException
	{
		final byte[] raw = new byte[bytes];
		new SecureRandom().nextBytes( raw );
		final byte[] encoded = Base64.getUrlEncoder().encode( raw );

		return new String( encoded, ENCODING );
	}

	/**
	 * Create and encode a new tracker counter.
	 *
	 * @param clock
	 *            clock for current time in milliseconds
	 *
	 * @return tracker counter
	 * @throws UnsupportedEncodingException
	 *             if unable to encode the bytes
	 */
	protected String createCounter( final Clock clock ) throws UnsupportedEncodingException
	{
		final Long now = clock.millis();
		final byte[] time = ByteBuffer.allocate( Long.SIZE / Byte.SIZE ).putLong( now ).array();
		final int i = firstNonZeroByte( time );
		final byte[] raw = ArrayUtils.subarray( time, i, time.length );
		final byte[] encoded = Base64.getUrlEncoder().encode( raw );

		return new String( encoded, ENCODING );
	}

	/**
	 * Decode the tracker counter.
	 *
	 * @param counter
	 *            counter to decode
	 *
	 * @return time of counter
	 */
	protected long decodeCounter( final String counter )
	{
		final byte[] decoded = Base64.getUrlDecoder().decode( counter );
		final byte[] raw = new byte[Long.SIZE / Byte.SIZE];
		Arrays.fill( raw, ( byte ) 0 );
		System.arraycopy( decoded, 0, raw, raw.length - decoded.length, decoded.length );

		return ByteBuffer.allocate( Long.SIZE / Byte.SIZE ).put( raw ).getLong( 0 );
	}

	private int firstNonZeroByte( final byte[] time )
	{
		int i = 0;
		for ( final byte b : time )
		{
			if ( b == 0 )
			{
				i++;
			}
			else
			{
				break;
			}
		}
		return i;
	}

}
