/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time.lunar;


import java.math.BigDecimal;


/**
 * Lunar Calculations.
 */
public interface LunarCalculator
{

	/**
	 * The average length of an Anomalistic month.
	 */
	public static final BigDecimal AVG_ANOMALISTIC_MONTH = new BigDecimal( "27.55454988" );

	/**
	 * The average length of an Synodic month.
	 */
	public static final BigDecimal AVG_SYNODIC_MONTH = new BigDecimal( "29.530588853" );

	/**
	 * The length of the Full Moon Cycle.
	 * <p>
	 * <img src="http://upload.wikimedia.org/math/7/b/d/7bd2a28e7dd5a827f146d85eba2ec3c5.png"
	 * alt="Full Moon Cycle Formula">
	 */
	public static final BigDecimal FULL_MOON_CYCLE = AVG_ANOMALISTIC_MONTH.multiply( AVG_SYNODIC_MONTH ).divide(
			AVG_ANOMALISTIC_MONTH.add( AVG_SYNODIC_MONTH ) );

}
