package uk.co.bluegecko.core.server.tracker;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.Principal;
import java.time.Clock;
import java.time.Instant;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
public class TrackerTokenFilterTest
{

	private TrackerTokenFilter filter;
	private ContainerRequestContext requestContext;
	private ContainerResponseContext responseContext;
	private MultivaluedMap< String, Object > headers;

	@Before
	public void setUp() throws Exception
	{
		filter = new TrackerTokenFilter();
		requestContext = mock( ContainerRequestContext.class );
		responseContext = mock( ContainerResponseContext.class );

		final SecurityContext securityContext = setUpSecurityContext();
		when( requestContext.getSecurityContext() ).thenReturn( securityContext );

		final UriInfo uriInfo = setUpUriInfo();
		when( requestContext.getUriInfo() ).thenReturn( uriInfo );

		headers = new MultivaluedHashMap< >();
		when( responseContext.getHeaders() ).thenReturn( headers );

		MDC.clear();
	}

	@Test
	public final void testInitialRequest() throws IOException
	{
		filter.filter( requestContext );

		assertThat( MDC.get( TrackerToken.TOKEN ), is( not( nullValue( String.class ) ) ) );
		assertThat( MDC.get( TrackerToken.COUNTER ), is( not( nullValue( String.class ) ) ) );
		assertThat( MDC.get( TrackerToken.USERNAME ), is( "test-user" ) );
	}

	@Test
	public final void testFollowupRequest() throws IOException
	{
		when( requestContext.getHeaderString( TrackerToken.TOKEN ) ).thenReturn( "token" );

		filter.filter( requestContext );

		assertThat( MDC.get( TrackerToken.TOKEN ), is( "token" ) );
		assertThat( MDC.get( TrackerToken.COUNTER ), is( not( nullValue( String.class ) ) ) );
		assertThat( MDC.get( TrackerToken.USERNAME ), is( "test-user" ) );
	}

	@Test
	public final void testInitialResponse() throws IOException
	{
		MDC.put( TrackerToken.TOKEN, "token" );
		MDC.put( TrackerToken.COUNTER, "123" );

		filter.filter( requestContext, responseContext );

		assertThat( headers.containsKey( TrackerToken.TOKEN ), is( true ) );
		assertThat( headers.containsKey( TrackerToken.COUNTER ), is( false ) );
	}

	@Test
	public final void testCounterEncoding() throws UnsupportedEncodingException
	{
		final String counter = filter.createCounter( getFixedClock() );
		assertThat( counter.length(), is( 8 ) );
		assertThat( counter, is( "ASk2VJYA" ) );
	}

	@Test
	public final void testCounterDecoding()
	{
		final long time = filter.decodeCounter( "ASk2VJYA" );
		assertThat( time, is( getInstant().toEpochMilli() ) );
	}

	private Clock getFixedClock()
	{
		return TestHarness.getFixedClock();
	}

	private Instant getInstant()
	{
		return TestHarness.getInstant();
	}

	private UriInfo setUpUriInfo()
	{
		final UriInfo uriInfo = mock( UriInfo.class );
		when( uriInfo.getRequestUri() ).thenReturn( URI.create( "http://localhost:8080/test/call" ) );
		return uriInfo;
	}

	private SecurityContext setUpSecurityContext()
	{
		final SecurityContext securityContext = mock( SecurityContext.class );
		final Principal principal = mock( Principal.class );
		when( principal.getName() ).thenReturn( "test-user" );
		when( securityContext.getUserPrincipal() ).thenReturn( principal );
		return securityContext;
	}

}
