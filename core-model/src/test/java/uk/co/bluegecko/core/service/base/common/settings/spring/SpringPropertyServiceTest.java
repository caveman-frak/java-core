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

import uk.co.bluegecko.core.model.key.Defaulted;
import uk.co.bluegecko.core.model.key.Key;
import uk.co.bluegecko.core.service.common.settings.PropertyService;
import uk.co.bluegecko.core.test.harness.TestHarness;


@SuppressWarnings( "javadoc" )
@Configuration
@PropertySource( "classpath:test.properties" )
public class SpringPropertyServiceTest extends TestHarness
{

	private enum TestSetting implements Key< TestSetting >
	{
		FOO, BAR
	}

	private enum TestDefaultedSetting implements Key< TestDefaultedSetting >, Defaulted< String >
	{
		FOO( "Foo" ), BAR( "Bar" );

		private final String defaultValue;

		private TestDefaultedSetting( final String defaultValue )
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
		assertThat( propertyService.has( TestSetting.FOO ), is( true ) );
	}

	@Test
	public final void testFooValue()
	{
		assertThat( propertyService.property( TestSetting.FOO ), is( "Hello Foo" ) );
	}

	@Test
	public final void testBarExists()
	{
		assertThat( propertyService.has( TestSetting.BAR ), is( false ) );
	}

	@Test
	public final void testBarValue()
	{
		assertThat( propertyService.property( TestSetting.BAR ), is( ( String ) null ) );
	}

	@Test
	public final void testBarDefault()
	{
		assertThat( propertyService.property( TestSetting.BAR, "GoodBye" ), is( "GoodBye" ) );
	}

	@Test
	public final void testFooValueDefaulted()
	{
		assertThat( propertyService.property( TestSetting.FOO ), is( "Hello Foo" ) );
	}

	@Test
	public final void testBarValueDefaulted()
	{
		assertThat( propertyService.property( TestDefaultedSetting.BAR ), is( "Bar" ) );
	}

}
