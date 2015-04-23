/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


import java.io.Serializable;
import java.util.Set;


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
	 *
	 */
	public enum Severity
	{
		/**
		 * An Information message.
		 */
		INFO,
		/**
		 * A Warning message.
		 */
		WARN,
		/**
		 * An Error message.
		 */
		ERROR
	}

	/**
	 * Determine if there are any messages of the passed severity.
	 *
	 * @param severity
	 *            severity of message to check for
	 * @return true if messages exist
	 */
	public boolean hasMessages( Severity severity );

	/**
	 * Determine if there are any messages of the passed severity and key.
	 *
	 * @param severity
	 *            severity of message to check for
	 * @param key
	 *            the message key to get
	 * @return true if messages exist
	 */
	public boolean hasMessages( Severity severity, String key );

	/**
	 * Get a list of keys for messages oif the passed severity.
	 *
	 * @param severity
	 *            severity of message to get keys for
	 * @return set of message keys
	 */
	public Set< String > getKeys( Severity severity );

	/**
	 * Get all messages for a specific key
	 *
	 * @param severity
	 *            severity of message to get
	 * @param key
	 *            the message key to get
	 * @return set of messages
	 */
	public Set< String > getMessages( Severity severity, String key );

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
	public Set< String > addMessages( Severity severity, String key, String... messages );

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
