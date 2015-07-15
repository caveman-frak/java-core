package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.config.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_PASSWORD;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_USER;
import static uk.co.bluegecko.core.server.config.TestServerConstants.PORT;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.INFO;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.PATH;

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

import uk.co.bluegecko.core.ApplicationConfig;
import uk.co.bluegecko.core.server.interceptor.AcceptHeaderHttpRequestInterceptor;
import uk.co.bluegecko.core.server.model.base.BaseHealth;


@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = ApplicationConfig.class )
@WebAppConfiguration
@IntegrationTest( "server.port=" + PORT )
public class HealthResourceIntegrationTest
{

	private final RestTemplate restTemplate = new TestRestTemplate( TEST_USER, TEST_PASSWORD );

	private URI serverUrl;

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		serverUrl = new URL( "http", "localhost", PORT, BASE_PATH + PATH + INFO ).toURI();
	}

	@Test
	public void fetchHealthAsJson() throws RestClientException
	{
		restTemplate.setInterceptors( Arrays
				.asList( new AcceptHeaderHttpRequestInterceptor( MediaType.APPLICATION_JSON ) ) );
		final ResponseEntity< BaseHealth > entity = restTemplate.getForEntity( serverUrl, BaseHealth.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( entity.getHeaders().getContentType().includes( MediaType.APPLICATION_JSON ), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

	@Test
	public void fetchHealthAsXml()
	{
		restTemplate.setInterceptors( Arrays
				.asList( new AcceptHeaderHttpRequestInterceptor( MediaType.APPLICATION_XML ) ) );
		final ResponseEntity< BaseHealth > entity = restTemplate.getForEntity( serverUrl, BaseHealth.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( entity.getHeaders().getContentType().includes( MediaType.APPLICATION_XML ), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

}
