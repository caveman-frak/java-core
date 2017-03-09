package uk.co.bluegecko.core.server.test.interceptor;


import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;


public abstract class AbstractHeaderHttpRequestInterceptor< T > implements ClientHttpRequestInterceptor
{

	private final T headerValue;

	public AbstractHeaderHttpRequestInterceptor( final T headerValue )
	{
		super();

		this.headerValue = headerValue;
	}

	@Override
	public ClientHttpResponse intercept( final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution ) throws IOException
	{
		final HttpRequestWrapper requestWrapper = new HttpRequestWrapper( request );

		setHeader( requestWrapper );

		return execution.execute( requestWrapper, body );
	}

	protected abstract void setHeader( final HttpRequestWrapper requestWrapper );

	protected T getHeaderValue()
	{
		return headerValue;
	}

}