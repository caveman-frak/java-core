package uk.co.bluegecko.examples.models;


import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


/**
 * Enumeration of common titles of address,
 */
@BaseName( "uk.co.bluegecko.examples.models.Title" )
@LocaleData(
	{ @ch.qos.cal10n.Locale( "en" ) } )
public enum Title
{

	/**
	 * Blank
	 */
	NONE,
	/**
	 * Mr
	 */
	MR,
	/**
	 * Ms
	 */
	MS,
	/**
	 * Miss
	 */
	MISS,
	/**
	 * Mrs
	 */
	MRS,
	/**
	 * Doctor
	 */
	DOCTOR,
	/**
	 * Professor
	 */
	PROFESSOR

}
