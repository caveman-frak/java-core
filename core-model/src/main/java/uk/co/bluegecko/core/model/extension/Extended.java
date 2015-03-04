/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.extension;


import java.util.List;

import uk.co.bluegecko.core.model.Model;


/**
 * Used to add extension functionality to a model.
 *
 * @param <M>
 *            the class of model to extend
 */
public interface Extended< M extends Model >
{

	/**
	 * Set the available extensions for this model.
	 *
	 * @param extensions
	 *            list of extensions for this model
	 */
	public void setExtensions( List< Extension< M > > extensions );

	/**
	 * The list of available extensions for this model.
	 *
	 * @return all available extensions
	 */
	public List< Extension< M > > getExtensions();

	/**
	 * An extension of a specific class.
	 *
	 * @param <T>
	 *            class of the extension to get
	 * @param klass
	 *            the class of extension to return
	 * @return an extension of that class, if available, otherwise null
	 */
	public < T extends Extension< M > > T getExtension( Class< T > klass );

	/**
	 * Apply to each registered extension
	 */
	public void applyExtension();

}
