package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.ServerConstants.PATH;
import static uk.co.bluegecko.core.server.TestServerConstants.PORT;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import uk.co.bluegecko.core.Application;
import uk.co.bluegecko.core.server.interceptor.AcceptHeaderHttpRequestInterceptor;
import uk.co.bluegecko.core.server.model.Health;


@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = Application.class )
@WebAppConfiguration
@IntegrationTest( "server.port=" + PORT )
public class HealthResourceIntegrationTest
{

	private final RestTemplate restTemplate = new TestRestTemplate();

	private URI serverUrl;

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		serverUrl = new URL( "http", "localhost", PORT, PATH + "/health" ).toURI();
	}

	@Test
	public void healthAsJson() throws RestClientException
	{
		restTemplate.setInterceptors( Arrays
				.asList( new AcceptHeaderHttpRequestInterceptor( MediaType.APPLICATION_JSON ) ) );
		final ResponseEntity< Health > entity = restTemplate.getForEntity( serverUrl, Health.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( entity.getHeaders().getContentType().includes( MediaType.APPLICATION_JSON ), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

	@Test
	public void healthAsXml()
	{
		restTemplate.setInterceptors( Arrays
				.asList( new AcceptHeaderHttpRequestInterceptor( MediaType.APPLICATION_XML ) ) );
		final ResponseEntity< Health > entity = restTemplate.getForEntity( serverUrl, Health.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( entity.getHeaders().getContentType().includes( MediaType.APPLICATION_XML ), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

}
