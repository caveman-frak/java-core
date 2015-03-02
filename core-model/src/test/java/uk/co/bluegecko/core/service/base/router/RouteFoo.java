/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.router;

import uk.co.bluegecko.core.service.base.router.RoutingPath;


/**
 *
 */
public interface RouteFoo
{

	/**
	 * @param path
	 *            the path to route this by
	 * @param sequence
	 *            sequence of characters
	 * @return number of characters
	 */
	public int count( @RoutingPath String path, CharSequence sequence );

	/**
	 *
	 */
	public void bar();

}
