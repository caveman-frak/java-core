package uk.co.bluegecko.core.model;


import java.util.Locale;

import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 * Message holder, supports late localisation of message.
 *
 */
public interface Message
{

	/**
	 * Construct the localised version of the message for the passed locale.
	 * 
	 * @param localisationService
	 *            service to use to localise the message and its arguments
	 * @param locale
	 *            locale to use
	 * @return localised message
	 */
	public String getText( LocalisationService localisationService, Locale locale );

}
