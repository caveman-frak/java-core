package uk.co.bluegecko.core.server.filter;


import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;


/**
 * Add a tracker token into the logging context for incoming requests
 *
 */
public class TrackerTokenFilter implements ContainerRequestFilter, ContainerResponseFilter
{

	private static final String TOKEN = "X-Trace";

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
	}

}
