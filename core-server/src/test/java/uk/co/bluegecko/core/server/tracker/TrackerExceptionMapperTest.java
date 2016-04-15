package uk.co.bluegecko.core.server.tracker;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import uk.co.bluegecko.core.exceptions.ClientError;
import uk.co.bluegecko.core.exceptions.Payload;
import uk.co.bluegecko.core.exceptions.ServerError;
import uk.co.bluegecko.core.server.exceptions.WebErrorStatus;


@SuppressWarnings( "javadoc" )
public class TrackerExceptionMapperTest
{

	private ExceptionMapper< Throwable > mapper;

	@Before
	public void setUp() throws Exception
	{
		mapper = new TrackerExceptionMapper( false );

		MDC.put( TrackerToken.TOKEN, "test" );
		MDC.put( TrackerToken.COUNTER, "123" );
	}

	@Test
	public final void testServerException()
	{
		final Response response = mapper.toResponse( new ServerException() );

		assertThat( response.getStatus(), is( Status.INTERNAL_SERVER_ERROR.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.TEXT_PLAIN_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) "test/123" ) );
	}

	@Test
	public final void testClientException()
	{
		final Response response = mapper.toResponse( new ClientException() );

		assertThat( response.getStatus(), is( Status.BAD_REQUEST.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.TEXT_PLAIN_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) "test/123" ) );
	}

	@Test
	public final void testWebException()
	{
		final Response response = mapper.toResponse( new WebException( Status.CONFLICT ) );

		assertThat( response.getStatus(), is( Status.CONFLICT.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.TEXT_PLAIN_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) "test/123" ) );
	}

	@Test
	public final void testWebExceptionNoStatus()
	{
		final Response response = mapper.toResponse( new WebException( null ) );

		assertThat( response.getStatus(), is( Status.INTERNAL_SERVER_ERROR.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.TEXT_PLAIN_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) "test/123" ) );
	}

	@Test
	public final void testUndefinedException()
	{
		final Response response = mapper.toResponse( new IllegalArgumentException() );

		assertThat( response.getStatus(), is( Status.INTERNAL_SERVER_ERROR.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.TEXT_PLAIN_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) "test/123" ) );
	}

	@Test
	public final void testNestedClientException()
	{
		final Response response = mapper.toResponse( new IllegalArgumentException( new ClientException() ) );

		assertThat( response.getStatus(), is( Status.BAD_REQUEST.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.TEXT_PLAIN_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) "test/123" ) );
	}

	@Test
	public final void testPayloadException()
	{
		final Response response = mapper.toResponse( new PayloadException( BigInteger.TEN ) );

		assertThat( response.getStatus(), is( Status.BAD_REQUEST.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.APPLICATION_JSON_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) BigInteger.TEN ) );
	}

	@Test
	public final void testNestedPayloadException()
	{
		final Response response = mapper
				.toResponse( new IllegalArgumentException( new PayloadException( BigInteger.TEN ) ) );

		assertThat( response.getStatus(), is( Status.BAD_REQUEST.getStatusCode() ) );
		assertThat( response.getMediaType(), is( MediaType.APPLICATION_JSON_TYPE ) );
		assertThat( response.getEntity(), is( ( Object ) BigInteger.TEN ) );
	}

	private static final class ServerException extends RuntimeException implements ServerError
	{

		private static final long serialVersionUID = 1L;

		public ServerException()
		{}

	}

	private static final class ClientException extends RuntimeException implements ClientError
	{

		private static final long serialVersionUID = 1L;

		public ClientException()
		{}

	}

	private static final class WebException extends RuntimeException implements WebErrorStatus
	{

		private static final long serialVersionUID = 1L;

		private final Status status;

		public WebException( final Status status )
		{
			this.status = status;
		}

		@Override
		public Status getStatus()
		{
			return status;
		}

	}

	private static final class PayloadException extends RuntimeException implements ClientError, Payload< BigInteger >
	{

		private static final long serialVersionUID = 1L;

		private final BigInteger payload;

		public PayloadException( final BigInteger payload )
		{
			this.payload = payload;
		}

		@Override
		public BigInteger payload()
		{
			return payload;
		}

	}

}
