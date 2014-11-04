/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.provider;


/**
 *
 */
public interface ProviderFoo
{

	/**
	 * @param sequence
	 *            sequence of characters
	 * @return number of characters
	 */
	public int count( CharSequence sequence );

	/**
	 * @param sequence
	 *            sequence of characters
	 * @return array of characters
	 */
	public char[] chars( CharSequence sequence );

	/**
	 *
	 */
	public void bar();

}
