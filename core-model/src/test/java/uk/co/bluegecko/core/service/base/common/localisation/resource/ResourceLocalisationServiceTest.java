/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.common.localisation.resource;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.bluegecko.core.test.matcher.logging.LoggingEventMatcher.info;

import java.util.Locale;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService;
import uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService.Log;
import uk.co.bluegecko.core.service.common.LocaleService;
import uk.co.bluegecko.core.service.common.LocalisationService;
import uk.co.bluegecko.core.test.harness.TestHarness;

import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;


/**
 *
 */
public class ResourceLocalisationServiceTest extends TestHarness
{

	private static final String BUNDLE_NAME = ResourceLocalisationServiceTest.class.getName();
	private static final String MISSING_BUNDLE_NAME = BUNDLE_NAME + "X";
	private static Locale current;

	private LocalisationService localisationService;
	private LocaleService localeService;

	/**
	 * Save the current locale
	 */
	@BeforeClass
	public static final void saveCurrentLocale()
	{
		current = Locale.getDefault();
		Locale.setDefault( Locale.ROOT );
	}

	/**
	 * Restore the current locale
	 */
	@AfterClass
	public static final void restoreCurrentLocale()
	{
		Locale.setDefault( current );
	}

	/**
	 * Initialise the test
	 */
	@Before
	public final void setUp()
	{
		localeService = mock( LocaleService.class );
		when( localeService.getUserLocale() ).thenReturn( Locale.ROOT );
		when( localeService.getSystemLocale() ).thenReturn( Locale.ENGLISH );
		localisationService = new ResourceLocalisationService( localeService );
	}

	/**
	 * Test the localisation bundles
	 */
	@Test
	public final void testLogMessages()
	{
		verifyLocalisation( ResourceLocalisationService.Log.class );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessage(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageExists()
	{
		assertThat( localisationService.getMessage( "exists", BUNDLE_NAME ), is( "exists" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessage(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageNotExists()
	{

		assertThat( localisationService.getMessage( "not-exists", BUNDLE_NAME ), is( "**not-exists**" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessage(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageNotExistsLogged()
	{
		final TestLogger testLogger = TestLoggerFactory.getTestLogger( ResourceLocalisationService.class );

		assertThat( localisationService.getMessage( "not-exists", BUNDLE_NAME ), is( "**not-exists**" ) );

		assertThat( testLogger.getLoggingEvents(), hasItem( info( Log.MISSING_KEY, BUNDLE_NAME, "not-exists" ) ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessage(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageFormat()
	{
		assertThat( localisationService.getMessage( "hello", BUNDLE_NAME, "World" ), is( "Hello World!" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessage(java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageMissingBundle()
	{
		assertThat( localisationService.getMessage( "exists", MISSING_BUNDLE_NAME ), is( "--exists--" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages()
	{
		final Map< String, Object > messages = localisationService.getMessages( BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "exists" ) );
		assertThat( messages, hasEntry( "colour", "colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_en()
	{
		when( localeService.getUserLocale() ).thenReturn( Locale.ENGLISH );
		final Map< String, Object > messages = localisationService.getMessages( BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Exists" ) );
		assertThat( messages, hasEntry( "colour", "Colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_en_GB()
	{
		when( localeService.getUserLocale() ).thenReturn( Locale.UK );
		final Map< String, Object > messages = localisationService.getMessages( BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Exists" ) );
		assertThat( messages, hasEntry( "colour", "Colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_en_US()
	{
		when( localeService.getUserLocale() ).thenReturn( Locale.US );
		final Map< String, Object > messages = localisationService.getMessages( BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Exists" ) );
		assertThat( messages, hasEntry( "colour", "Color" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_fr()
	{
		when( localeService.getUserLocale() ).thenReturn( Locale.FRENCH );
		final Map< String, Object > messages = localisationService.getMessages( BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Existe" ) );
		assertThat( messages, hasEntry( "colour", "Couleur" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_zh()
	{
		when( localeService.getUserLocale() ).thenReturn( Locale.CHINESE );
		final Map< String, Object > messages = localisationService.getMessages( BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "exists" ) );
		assertThat( messages, hasEntry( "colour", "colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#getMessages(java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessagesMissingBundle()
	{
		assertThat( localisationService.getMessages( MISSING_BUNDLE_NAME ).keySet(), is( empty() ) );
	}

}
