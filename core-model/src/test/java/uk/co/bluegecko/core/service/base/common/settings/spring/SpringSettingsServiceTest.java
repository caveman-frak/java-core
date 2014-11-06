/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.settings.spring;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import uk.co.bluegecko.core.service.common.settings.Setting;
import uk.co.bluegecko.core.service.common.settings.SettingsService;
import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
@Configuration
@PropertySource( "classpath:test.properties" )
public class SpringSettingsServiceTest extends TestHarness
{

	private static final Setting< Long > FOO = Setting.setting( "foo", Long.class );
	private static final Setting< String > BAR = Setting.setting( "bar", String.class, "Bar!" );

	@Autowired
	private SettingsService settingsService;

	@Test
	public final void testFooExists()
	{
		assertThat( settingsService.hasSetting( FOO ), is( true ) );
	}

	@Test
	public final void testFooValue()
	{
		assertThat( settingsService.getSetting( FOO ), is( 1001L ) );
	}

	@Test
	public final void testBarExists()
	{
		assertThat( settingsService.hasSetting( BAR ), is( false ) );
	}

	@Test
	public final void testBarValue()
	{
		assertThat( settingsService.getSetting( BAR ), is( "Bar!" ) );
	}

	@Test
	public final void testBarDefault()
	{
		assertThat( settingsService.getSetting( BAR, "GoodBye" ), is( "GoodBye" ) );
	}

}
