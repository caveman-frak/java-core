/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
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

import uk.co.bluegecko.core.service.base.common.localisation.resource.BaseLocalisationService.Log;
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
		when( localeService.user() ).thenReturn( Locale.ROOT );
		when( localeService.system() ).thenReturn( Locale.ENGLISH );
		localisationService = new ResourceLocalisationService( getApplicationContext(), localeService );
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
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#message(Locale, java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageExists()
	{
		assertThat( localisationService.message( null, BUNDLE_NAME, "exists" ), is( "exists" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#message(Locale, java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageNotExists()
	{

		assertThat( localisationService.message( null, BUNDLE_NAME, "not-exists" ), is( "**not-exists**" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#message(Locale, java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageNotExistsLogged()
	{
		final TestLogger testLogger = TestLoggerFactory.getTestLogger( ResourceLocalisationService.class );

		assertThat( localisationService.message( null, BUNDLE_NAME, "not-exists" ), is( "**not-exists**" ) );

		assertThat( testLogger.getLoggingEvents(), hasItem( info( Log.MISSING_KEY, Locale.ROOT, BUNDLE_NAME,
				"not-exists", "Can't find resource for bundle java.util.PropertyResourceBundle, key not-exists" ) ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#message(Locale, java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageFormat()
	{
		assertThat( localisationService.message( null, BUNDLE_NAME, "hello", "World" ), is( "Hello World!" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#message(Locale, java.lang.String, java.lang.String, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetMessageMissingBundle()
	{
		assertThat( localisationService.message( null, MISSING_BUNDLE_NAME, "exists" ), is( "--exists--" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages()
	{
		final Map< String, Object > messages = localisationService.messages( null, BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "exists" ) );
		assertThat( messages, hasEntry( "colour", "colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_en()
	{
		when( localeService.user() ).thenReturn( Locale.ENGLISH );
		final Map< String, Object > messages = localisationService.messages( null, BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Exists" ) );
		assertThat( messages, hasEntry( "colour", "Colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_en_GB()
	{
		when( localeService.user() ).thenReturn( Locale.UK );
		final Map< String, Object > messages = localisationService.messages( null, BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Exists" ) );
		assertThat( messages, hasEntry( "colour", "Colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_en_US()
	{
		when( localeService.user() ).thenReturn( Locale.US );
		final Map< String, Object > messages = localisationService.messages( null, BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Exists" ) );
		assertThat( messages, hasEntry( "colour", "Color" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_fr()
	{
		when( localeService.user() ).thenReturn( Locale.FRENCH );
		final Map< String, Object > messages = localisationService.messages( null, BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "Existe" ) );
		assertThat( messages, hasEntry( "colour", "Couleur" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessages_zh()
	{
		when( localeService.user() ).thenReturn( Locale.CHINESE );
		final Map< String, Object > messages = localisationService.messages( null, BUNDLE_NAME );
		assertThat( messages, hasEntry( "exists", "exists" ) );
		assertThat( messages, hasEntry( "colour", "colour" ) );
	}

	/**
	 * Test method for
	 * {@link uk.co.bluegecko.core.service.base.common.localisation.resource.ResourceLocalisationService#messages(Locale, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetMessagesMissingBundle()
	{
		assertThat( localisationService.messages( null, MISSING_BUNDLE_NAME ).keySet(), is( empty() ) );
	}

}
