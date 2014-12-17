/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time.lunar;


/**
 * The 4 main phases of the moon. Not including intermediate waxing / waning stages.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Lunar_phase">Lunar Phase</a>
 */
public enum MoonPhase
{

	/**
	 * New moon, not visible (too close to the Sun).
	 */
	NEW,
	/**
	 * Quarter moon, right side 50% lit (Northern hemisphere).
	 */
	FIRST_QUARTER,
	/**
	 * Full moon, completely illuminated disc.
	 */
	FULL,
	/**
	 * Quarter moon, left side 50% lit (Northern hemisphere).
	 */
	LAST_QUARTER

}
