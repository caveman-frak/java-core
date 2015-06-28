package uk.co.bluegecko.core.server.interceptor;


import java.io.IOException;
import java.util.Arrays;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;


@SuppressWarnings( "javadoc" )
public final class AcceptHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor
{

	private final MediaType headerValue;

	public AcceptHeaderHttpRequestInterceptor( final MediaType headerValue )
	{
		this.headerValue = headerValue;
	}

	public AcceptHeaderHttpRequestInterceptor( final String headerValue )
	{
		this.headerValue = MediaType.valueOf( headerValue );
	}

	@Override
	public ClientHttpResponse intercept( final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution ) throws IOException
	{

		final HttpRequestWrapper requestWrapper = new HttpRequestWrapper( request );
		requestWrapper.getHeaders().setAccept( Arrays.asList( headerValue ) );

		return execution.execute( requestWrapper, body );
	}
}