/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.provider;


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
