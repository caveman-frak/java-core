/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.router;


/**
 * This object can be routed according to the supplied path
 */
public interface Routable
{

	/**
	 * The routing path for this object
	 * 
	 * @return the path
	 */
	public String path();

}
