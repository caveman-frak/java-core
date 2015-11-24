package uk.co.bluegecko.core.server.filter;


import java.io.IOException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;

import javax.servlet.annotation.WebFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import ch.qos.cal10n.MessageConveyor;


/**
 * Add a tracker token into the logging context for incoming requests
 *
 */
@Provider
@WebFilter
public class TrackerTokenFilter implements ContainerRequestFilter, ContainerResponseFilter
{

	private static final String UNAUTHENTICATED = "??";

	/** Log messages. */
	@BaseName( "uk.co.bluegecko.core.server.filter.TrackerTokenFilter$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		/** Log request */
		REQUEST,
		/** Log response */
		RESPONSE;
	}

	private static final String TOKEN = "X-Trace";
	private static final String USERNAME = "X-UserName";

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
			final byte[] raw = new byte[12];
			new SecureRandom().nextBytes( raw );
			final byte[] encoded = Base64.getUrlEncoder().encode( raw );

			token = new String( encoded, "UTF8" );
		}
		MDC.put( TOKEN, token );

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

		if ( logger.isDebugEnabled() )
		{
			logger.debug( Log.RESPONSE, requestContext.getMethod(), requestContext.getUriInfo().getRequestUri() );
		}
	}

}
