/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.settings.spring;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import uk.co.bluegecko.core.model.key.TypedKey;
import uk.co.bluegecko.core.service.common.settings.SettingsService;
import uk.co.bluegecko.core.test.harness.TestHarness;


@Configuration
@PropertySource( "classpath:test.properties" )
public class SpringSettingsServiceTest extends TestHarness
{

	private static final TypedKey< Long > FOO = TypedKey.key( "foo", Long.class );
	private static final TypedKey< String > BAR = TypedKey.key( "bar", String.class, "Bar!" );

	@Autowired
	private SettingsService settingsService;

	@Test
	public final void testFooExists()
	{
		assertThat( settingsService.has( FOO ), is( true ) );
	}

	@Test
	public final void testFooValue()
	{
		assertThat( settingsService.setting( FOO ), is( 1001L ) );
	}

	@Test
	public final void testBarExists()
	{
		assertThat( settingsService.has( BAR ), is( false ) );
	}

	@Test
	public final void testBarValue()
	{
		assertThat( settingsService.setting( BAR ), is( "Bar!" ) );
	}

	@Test
	public final void testBarDefault()
	{
		assertThat( settingsService.setting( BAR, "GoodBye" ), is( "GoodBye" ) );
	}

}
