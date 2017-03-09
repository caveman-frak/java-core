/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.time;


import java.time.LocalDate;
import java.time.Month;


public interface TemporalTestBase
{

	public static final int YEAR = 2012;
	public static final LocalDate YEAR_START = LocalDate.of( YEAR, Month.JANUARY, 1 );
	public static final LocalDate YEAR_END = LocalDate.of( YEAR, Month.DECEMBER, 31 );

}