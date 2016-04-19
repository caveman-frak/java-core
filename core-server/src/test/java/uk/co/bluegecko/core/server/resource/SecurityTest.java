package uk.co.bluegecko.core.server.resource;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static uk.co.bluegecko.core.server.config.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_PASSWORD;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_USER;
import static uk.co.bluegecko.core.server.config.ServerConstants.Roles.USER;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.GC;
import static uk.co.bluegecko.core.server.resource.ResourceConstants.Health.PATH;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import uk.co.bluegecko.core.server.test.AbstractWebTest;


@SuppressWarnings( "javadoc" )
public class SecurityTest extends AbstractWebTest
{

	private MockMvc mvc;
	private String path;

	@Before
	public void setUp()
	{
		path = BASE_PATH + PATH + GC;
		mvc = MockMvcBuilders.webAppContextSetup( getContext() ).apply( springSecurity() ).build();
	}

	@Test
	public void testLogin() throws Exception
	{
		mvc.perform( post( path ).with( httpBasic( TEST_USER, TEST_PASSWORD ) ).with( csrf() ) )
				.andExpect( authenticated().withRoles( USER ).withUsername( TEST_USER ) );
	}

	@Test
	public void testLoginWithoutCredentials() throws Exception
	{
		mvc.perform( post( path ).with( csrf() ) ).andExpect( unauthenticated() );
	}

	@Test
	public void testLoginWithInvalidCredentials() throws Exception
	{
		mvc.perform( post( path ).with( httpBasic( TEST_USER, "" ) ).with( csrf() ) ).andExpect( unauthenticated() );
	}

	@Test
	public void testLoginWithoutCsrf() throws Exception
	{
		mvc.perform( post( path ).with( httpBasic( TEST_USER, TEST_PASSWORD ) ) ).andExpect( unauthenticated() );
	}

	@Test
	public void testLogout() throws Exception
	{
		mvc.perform( post( path ).with( httpBasic( TEST_USER, TEST_PASSWORD ) ).with( csrf() ) );
		mvc.perform( logout() );
	}

}