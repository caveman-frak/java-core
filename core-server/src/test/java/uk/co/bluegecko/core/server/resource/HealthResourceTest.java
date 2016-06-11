package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.config.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_PASSWORD;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_USER;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.INFO;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.PATH;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import uk.co.bluegecko.core.server.model.Health;
import uk.co.bluegecko.core.server.model.base.BaseHealth;
import uk.co.bluegecko.core.server.test.AbstractWebTest;


@SuppressWarnings( "javadoc" )
public class HealthResourceTest extends AbstractWebTest
{

	private TestRestTemplate testRestTemplate;

	private URI serverUrl;

	@Before
	public void setUp() throws MalformedURLException, URISyntaxException
	{
		testRestTemplate = new TestRestTemplate( TEST_USER, TEST_PASSWORD );
		serverUrl = new URL( "http", "localhost", getHttpPort(), BASE_PATH + PATH + INFO ).toURI();
	}

	@Test
	public void fetchHealthAsJson() throws RestClientException
	{
		final RestTemplate restTemplate = testRestTemplate.getRestTemplate();
		restTemplate.setMessageConverters( createJaxbMessageConverter() );
		restTemplate.setInterceptors( createMediaTypeInterceptor( MediaType.APPLICATION_JSON ) );
		final ResponseEntity< ? extends Health > entity = testRestTemplate.getForEntity( serverUrl, BaseHealth.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( entity.getHeaders().getContentType().includes( MediaType.APPLICATION_JSON ), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

	@Test
	public void fetchHealthAsXml()
	{
		final RestTemplate restTemplate = testRestTemplate.getRestTemplate();
		restTemplate.setInterceptors( createMediaTypeInterceptor( MediaType.APPLICATION_XML ) );
		final ResponseEntity< ? extends Health > entity = testRestTemplate.getForEntity( serverUrl, BaseHealth.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getHeaders(), hasKey( HttpHeaders.CONTENT_TYPE ) );
		assertThat( entity.getHeaders().getContentType().includes( MediaType.APPLICATION_XML ), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

}