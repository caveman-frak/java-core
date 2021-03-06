/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.extension;


import uk.co.bluegecko.core.model.Model;


/**
 * Test Model class Bar.
 */
public interface BarModel extends Model, Extended< BarModel >
{

	/**
	 * Do bar()
	 */
	public void bar();

}
