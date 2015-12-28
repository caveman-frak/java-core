/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


/**
 * A wrapper for message text.
 *
 * Contains messages grouped by severity and a message key.
 *
 */
public interface Messages extends Serializable
{

	/**
	 * The severity of the message.
	 */
	@BaseName( "uk.co.bluegecko.core.model.Messages$Severity" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) })
	public enum Severity
	{
		/** An Error message. */
		ERROR, /** A Warning message. */
		WARN, /** An Information message. */
		INFO, /** No messages */
		NONE
	}

	/**
	 * Set of Severities that are not NONE.
	 */
	public static final Set< Severity > NOT_NONE = EnumSet.of( Severity.ERROR, Severity.WARN, Severity.INFO );

	/**
	 * Return highest level of severity.
	 *
	 * @return severity
	 */
	public Severity severity();

	/**
	 * Determine if there are any messages of the passed severity.
	 *
	 * @param severity
	 *            severity of message to check for
	 * @return true if messages exist
	 */
	public boolean has( Severity severity );

	/**
	 * Determine if there are any messages that exceed the passed severity.
	 *
	 * @param severity
	 *            severity of message to check for
	 * @return true if messages exist
	 */
	public boolean exceeds( Severity severity );

	/**
	 * Determine if there are any messages of any of the passed severity.
	 *
	 * @param severities
	 *            set of severity of message to check for
	 * @return true if any messages exist
	 * @throws IllegalArgumentException
	 *             if the set contains severity NONE
	 */
	public boolean has( Set< Severity > severities );

	/**
	 * Determine if there are any messages of the passed severity and key.
	 *
	 * @param severity
	 *            severity of message to check for
	 * @param key
	 *            the message key to get
	 * @return true if messages exist
	 */
	public boolean has( Severity severity, String key );

	/**
	 * Get a list of keys for messages oif the passed severity.
	 *
	 * @param severity
	 *            severity of message to get keys for
	 * @return set of message keys
	 */
	public Set< String > keys( Severity severity );

	/**
	 * Get all messages for a specific key
	 *
	 * @param severity
	 *            severity of message to get
	 * @param key
	 *            the message key to get
	 * @return set of messages
	 */
	public Set< Message > messages( Severity severity, String key );

	/**
	 * Add new messages to the key.
	 *
	 * @param severity
	 *            severity of message
	 * @param key
	 *            message key
	 * @param messages
	 *            the text messages
	 * @return all text messages for this key
	 */
	public Set< Message > add( Severity severity, String key, Message... messages );

	/**
	 * Clear all messages.
	 */
	public void clear();

	/**
	 * Clear all messages for this severity/
	 *
	 * @param severity
	 *            severity of messages to clear
	 */
	public void clear( Severity severity );

	/**
	 * Clear all messages for this severity/
	 *
	 * @param severity
	 *            severity of messages to clear
	 * @param key
	 *            the message key to clear
	 */
	public void clear( Severity severity, String key );

}
