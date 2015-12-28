/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.builder;


/**
 * Template interface for a builder class.
 *
 * @param <T>
 *            the type to be built
 * @param <B>
 *            the builder class
 */
public interface Builder< T, B extends Builder< T, B > >
{

	/**
	 * Build a new instance.
	 *
	 * @return the new instance
	 */
	public T build();

	/**
	 * Copy the builder including current fields.
	 *
	 * @return a copy of the initialised builder
	 */
	public B copy();

	/**
	 * Reset the fields on the builder.
	 *
	 * @return the builder
	 */
	public B reset();

	/**
	 * Initialise the fields on the builder using the passed example.
	 *
	 * @param example
	 *            the example to use for initialisation
	 *
	 * @return the builder
	 */
	public B from( final T example );

	/**
	 * Initialise the fields on the builder using the passed builder.
	 *
	 * @param builder
	 *            the builder to use for initialisation
	 *
	 * @return the builder
	 */
	public B from( final B builder );

}
