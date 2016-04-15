package uk.co.bluegecko.core.server.resource;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Test;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;

import uk.co.bluegecko.core.server.test.AbstractWebTest;


@SuppressWarnings( "javadoc" )
public class StaticContentTest extends AbstractWebTest
{

	@Test
	public void fetchIndexPageAsHtml() throws Exception
	{
		try (WebClient webClient = MockMvcWebClientBuilder.webAppContextSetup( getContext() ).contextPath( "" ).build())
		{
			final HtmlPage page = webClient.getPage( new URL( "http", "localhost", getHttpPort(), "/index.html" ) );

			assertThat( page.getTitleText(), is( "Index Page" ) );
			assertThat( page.getElementById( "title" ).getTextContent(), is( "Index Page" ) );
			assertThat( ( ( HtmlParagraph ) page.getElementByName( "caption" ) ).getTextContent(),
					is( "Hello World!" ) );
		}
	}

	@Test
	public void fetchTestPageAsHtml() throws Exception
	{
		try (WebClient webClient = MockMvcWebClientBuilder.webAppContextSetup( getContext() ).contextPath( "" ).build())
		{
			final HtmlPage page = webClient.getPage( new URL( "http", "localhost", getHttpPort(), "/test.html" ) );

			assertThat( page.getTitleText(), is( "Test Page" ) );

			assertThat( page.getBody().getElementsByTagName( "div" ).get( 0 ).getElementsByTagName( "h1" ).get( 0 )
					.getTextContent(), is( "Test Page" ) );
			assertThat( ( ( DomText ) page.getBody().getFirstByXPath( "div[1]/p[1]/text()" ) ).getTextContent(),
					is( "Hello World!" ) );
		}
	}

}
