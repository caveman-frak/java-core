/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.extension;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.test.harness.TestHarness;


/**
 * Test model extensions.
 */
public class ExtensionTest extends TestHarness
{

	@Autowired
	private FooModel fooModel;
	private BarModel barModel;

	/**
	 * Setup test.
	 */
	@Before
	public final void setUp()
	{
		barModel = getApplicationContext().getBean( BarModel.class );
	}

	/**
	 * Test the foo extended model.
	 */
	@Test
	public final void testFooModel()
	{
		assertThat( fooModel, is( not( nullValue() ) ) );
		assertThat( fooModel, isA( FooModel.class ) );
		final List< Extension< FooModel >> extensions = fooModel.getExtensions();
		assertThat( extensions, hasSize( 1 ) );
		assertThat( extensions, contains( isA( Extension.class ) ) );
		final FooExtension extension = fooModel.getExtension( FooExtension.class );
		assertThat( extension, is( not( nullValue() ) ) );
		assertThat( extension, isA( FooExtension.class ) );
	}

	/**
	 * Test the bar extended model.
	 */
	@SuppressWarnings( "unchecked" )
	@Test
	public final void testBarModel()
	{
		assertThat( barModel, is( not( nullValue() ) ) );
		assertThat( barModel, isA( BarModel.class ) );
		assertThat( barModel, isA( BarModel.class ) );
		final List< Extension< BarModel >> extensions = barModel.getExtensions();
		assertThat( extensions, hasSize( 2 ) );
		assertThat( extensions, contains( isA( Extension.class ), isA( Extension.class ) ) );
		final BarExtension1 extension1 = barModel.getExtension( BarExtension1.class );
		assertThat( extension1, is( not( nullValue() ) ) );
		assertThat( extension1, isA( BarExtension1.class ) );
		final BarExtension2 extension2 = barModel.getExtension( BarExtension2.class );
		assertThat( extension2, is( not( nullValue() ) ) );
		assertThat( extension2, isA( BarExtension2.class ) );
	}

}
