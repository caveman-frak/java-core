package uk.co.bluegecko.core.model;


import java.util.Locale;


/**
 * Message holder, supports late localisation of message.
 *
 */
public interface Message
{

	/**
	 * Construct the localised version of the message for the passed locale.
	 *
	 * @param locale
	 *            locale to use
	 * @return localised message
	 */
	public String getText( Locale locale );

}
