package uk.co.bluegecko.core.server.cucumber;


import org.junit.runner.RunWith;

import uk.co.bluegecko.core.server.test.AbstractIntegrationTest;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;


@SuppressWarnings( "javadoc" )
@RunWith( Cucumber.class )
@CucumberOptions( plugin =
	{ "pretty", "html:target/cucumber" }, snippets = SnippetType.CAMELCASE, glue =
	{ "uk.co.bluegecko.core.server.cucumber.steps", "uk.co.bluegecko.core.server.cucumber.hooks" }, strict = true )
public class CucumberIT extends AbstractIntegrationTest
{

}
