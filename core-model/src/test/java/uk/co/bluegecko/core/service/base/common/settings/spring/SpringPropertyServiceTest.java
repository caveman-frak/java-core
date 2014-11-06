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

import uk.co.bluegecko.core.service.common.settings.Defaulted;
import uk.co.bluegecko.core.service.common.settings.PropertyService;
import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
@Configuration
@PropertySource( "classpath:test.properties" )
public class SpringPropertyServiceTest extends TestHarness
{

	private enum TestSetting
	{
		FOO, BAR
	}

	private enum TestDefaultSetting implements Defaulted< String >
	{
		FOO( "Foo" ), BAR( "Bar" );

		private final String defaultValue;

		private TestDefaultSetting( final String defaultValue )
		{
			this.defaultValue = defaultValue;
		}

		@Override
		public String defaultValue()
		{
			return defaultValue;
		}
	}

	@Autowired
	private PropertyService propertyService;

	@Test
	public final void testFooExists()
	{
		assertThat( propertyService.hasProperty( TestSetting.FOO ), is( true ) );
	}

	@Test
	public final void testFooValue()
	{
		assertThat( propertyService.getProperty( TestSetting.FOO ), is( "Hello Foo" ) );
	}

	@Test
	public final void testBarExists()
	{
		assertThat( propertyService.hasProperty( TestSetting.BAR ), is( false ) );
	}

	@Test
	public final void testBarValue()
	{
		assertThat( propertyService.getProperty( TestSetting.BAR ), is( ( String ) null ) );
	}

	@Test
	public final void testBarDefault()
	{
		assertThat( propertyService.getProperty( TestSetting.BAR, "GoodBye" ), is( "GoodBye" ) );
	}

	@Test
	public final void testFooValueDefaulted()
	{
		assertThat( propertyService.getProperty( TestSetting.FOO ), is( "Hello Foo" ) );
	}

	@Test
	public final void testBarValueDefaulted()
	{
		assertThat( propertyService.getProperty( TestDefaultSetting.BAR ), is( "Bar" ) );
	}

}
