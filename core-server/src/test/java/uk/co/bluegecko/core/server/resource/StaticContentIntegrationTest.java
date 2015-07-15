package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.server.TestServerConstants.PORT;

import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import uk.co.bluegecko.core.ApplicationConfig;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;


@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = ApplicationConfig.class )
@WebAppConfiguration
@IntegrationTest( "server.port=" + PORT )
public class StaticContentIntegrationTest
{

	@Autowired
	private WebApplicationContext context;

	@Test
	public void fetchIndexPageAsHtml() throws Exception
	{
		try (WebClient webClient = MockMvcWebClientBuilder.webAppContextSetup( context ).contextPath( "" )
				.createWebClient())
		{
			final HtmlPage page = webClient.getPage( new URL( "http", "localhost", PORT, "/index.html" ) );

			assertThat( page.getTitleText(), is( "Index Page" ) );
			assertThat( page.getElementById( "title" ).getTextContent(), is( "Index Page" ) );
			final HtmlParagraph para = ( HtmlParagraph ) page.getElementByName( "caption" );
			assertThat( para.getTextContent(), is( "Hello World!" ) );
		}
	}

	@Test
	public void fetchTestPageAsHtml() throws Exception
	{
		try (WebClient webClient = MockMvcWebClientBuilder.webAppContextSetup( context ).contextPath( "" )
				.createWebClient())
		{
			final HtmlPage page = webClient.getPage( new URL( "http", "localhost", PORT, "/test.html" ) );

			assertThat( page.getTitleText(), is( "Test Page" ) );
			assertThat(
					page.getBody().getElementsByTagName( "div" ).get( 0 ).getChildNodes().get( 0 ).getTextContent(),
					is( "Test Page" ) );
			assertThat( ( ( DomText ) page.getBody().getFirstByXPath( "div[1]/p[1]/text()" ) ).getTextContent(),
					is( "Hello World!" ) );
		}
	}

}
