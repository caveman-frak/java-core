package uk.co.bluegecko.core.service.base.common.localisation.resource;


import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import uk.co.bluegecko.core.service.base.AbstractService;
import uk.co.bluegecko.core.service.common.LocaleService;
import uk.co.bluegecko.core.service.common.LocalisationService;
import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


/**
 * @author tim
 *
 */
public abstract class BaseLocalisationService extends AbstractService implements LocalisationService
{

	@BaseName( "uk.co.bluegecko.core.service.common.LocalisationService$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	protected enum Log
	{
		MISSING_KEY, MISSING_BUNDLE
	}

	protected static final String MISSING_BUNDLE_INDICATOR = "--";
	protected static final String MISSING_KEY_INDICATOR = "**";

	/**
	 * Default constructor, referencing the {@link LocaleService}
	 *
	 * @param applicationContext
	 *            used to set the application context
	 * @param localeService
	 *            used to determine the current locale
	 */
	@Autowired
	public BaseLocalisationService( final ApplicationContext applicationContext, final LocaleService localeService )
	{
		super( applicationContext, localeService );
	}

	/**
	 * Internal method for getting a {@link ResourceBundle} of the correct user locale
	 *
	 * @param locale
	 *            the default locale
	 * @param bundleName
	 * @return the bundle for the user's locale
	 */
	protected ResourceBundle getBundle( final Locale locale, final String bundleName )
	{
		final ResourceBundle bundle = ResourceBundle.getBundle( bundleName, getLocale( locale ) );
		return bundle;
	}

	/**
	 * Internal method for getting a locale if none supplied
	 *
	 * @param locale
	 *            the locale to use if supplied
	 * @return the user's locale
	 */
	protected Locale getLocale( final Locale locale )
	{
		return locale != null ? locale : getUserLocale();
	}

	/**
	 * Return the default user locale.
	 *
	 * @return user locale
	 */
	public Locale getUserLocale()
	{
		return getLocaleService().getUserLocale();
	}

	/**
	 * Return the default system locale.
	 *
	 * @return system locale
	 */
	public Locale getSystemLocale()
	{
		return getLocaleService().getSystemLocale();
	}

}
