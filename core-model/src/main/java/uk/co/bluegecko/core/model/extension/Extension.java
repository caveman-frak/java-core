/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.extension;


import uk.co.bluegecko.core.model.Model;


/**
 * @param <M>
 *            the type of model to apply this extension to
 *
 */
public interface Extension< M extends Model >
{

	/**
	 * Apply the extension to the model.
	 *
	 * @param model
	 *            the model to apply the extension to
	 */
	public void apply( M model );

}
