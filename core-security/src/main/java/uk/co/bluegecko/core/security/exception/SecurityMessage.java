/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.security.exception;


import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


/**
 * Enumeration for possible security exception messages.
 */
@BaseName( "uk.co.bluegecko.core.security.exceptions.SecurityMessage" )
@LocaleData(
	{ @ch.qos.cal10n.Locale( "en" ) } )
public enum SecurityMessage
{
	/**
	 * Indicate the action requires the user to be authorised.
	 */
	UNAUTHORISED,
	/**
	 * Indicate the action requires permissions not possessed by the user.
	 */
	FORBIDDEN
}