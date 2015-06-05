package uk.co.bluegecko.core.server.resources;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import uk.co.bluegecko.core.Application;
import uk.co.bluegecko.core.server.interceptor.AcceptHeaderHttpRequestInterceptor;
import uk.co.bluegecko.core.server.model.Health;


@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = Application.class )
@WebAppConfiguration
@IntegrationTest( "server.port=9000" )
public class HealthResourceIntegrationTest
{

	private final RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void health()
	{

		restTemplate.setInterceptors( Arrays.asList( new AcceptHeaderHttpRequestInterceptor( "application/json" ) ) );
		final ResponseEntity< Health > entity = restTemplate
				.getForEntity( "http://localhost:9000/health", Health.class );

		assertThat( entity.getStatusCode().is2xxSuccessful(), is( true ) );
		assertThat( entity.getBody().getStatus(), is( "Jersey: Up and Running!" ) );
	}

}
