package uk.co.bluegecko.core.server.test.interceptor;


import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.client.support.HttpRequestWrapper;


@SuppressWarnings( "javadoc" )
public final class AcceptHeaderHttpRequestInterceptor extends AbstractHeaderHttpRequestInterceptor< MediaType >
{

	public AcceptHeaderHttpRequestInterceptor( final MediaType headerValue )
	{
		super( headerValue );
	}

	public AcceptHeaderHttpRequestInterceptor( final String headerValue )
	{
		super( MediaType.valueOf( headerValue ) );
	}

	@Override
	protected void setHeader( final HttpRequestWrapper requestWrapper )
	{
		requestWrapper.getHeaders().setAccept( Arrays.asList( getHeaderValue() ) );
	}

}