package uk.co.bluegecko.core.server.cucumber.steps;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.bluegecko.core.server.cucumber.hooks.LocalisationWebClient;
import uk.co.bluegecko.core.test.cucumber.Cucumber;


@Cucumber
public class LocalisationStepDefinitions
{

	@Autowired
	private LocalisationWebClient webClient;
	private Locale locale;
	private String key;

	public LocalisationStepDefinitions()
	{}

	@Given( "^Localised colours have been defined for \"(.*?)\"$" )
	public void localisedColoursHaveBeenDefinedFor( final String locale ) throws Throwable
	{
		this.locale = Locale.forLanguageTag( locale );
	}

	@When( "^Request localised version of \"(.*?)\"$" )
	public void requestLocalisedVersionOf( final String key ) throws Throwable
	{
		this.key = key;
	}

	@Then( "^The response should be \"(.*?)\"$" )
	public void theResponseShouldBe( final String value ) throws Throwable
	{
		assertThat( webClient.request( locale, key ), is( value ) );
	}

}
