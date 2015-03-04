/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.quantity;


/**
 * A representation of a quantity of a given unit.
 *
 * @param <N>
 *            the measure of amount
 * @param <U>
 *            the type of unit
 */
public interface Quantity< N extends Number, U >
{

	/**
	 * The amount of the quantity.
	 *
	 * @return the amount of the quantity
	 */
	public N amount();

	/**
	 * The unit of the quantity.
	 *
	 * @return the unit of the quantity
	 */
	public U unit();

}
