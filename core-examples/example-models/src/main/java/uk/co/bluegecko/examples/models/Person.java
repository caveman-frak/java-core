package uk.co.bluegecko.examples.models;


import java.time.temporal.TemporalAccessor;

import uk.co.bluegecko.core.model.Data;
import uk.co.bluegecko.core.model.Named;


/**
 * Representation of a person.
 */
public interface Person extends Data< Long >, Named
{

	/**
	 * Title of person.
	 *
	 * @return title
	 */
	public Title getTitle();

	/**
	 * First name of person.
	 *
	 * @return first name
	 */
	public String getFirstName();

	/**
	 * Last (family) name of person.
	 *
	 * @return last name
	 */
	public String getLastName();

	/**
	 * Date of birth of the person. Either as a {@link java.time.LocalDate} or as a {@link java.time.MonthDay}
	 *
	 * @return date of birth
	 */
	public TemporalAccessor getDateOfBirth();

}
