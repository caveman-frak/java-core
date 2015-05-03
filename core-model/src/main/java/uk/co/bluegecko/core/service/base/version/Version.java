package uk.co.bluegecko.core.service.base.version;


import java.io.Serializable;


/**
 * Define a version.
 *
 */
public interface Version extends Comparable< Version >, Serializable
{

	/**
	 * Major version number.
	 * 
	 * @return major version number
	 */
	public int getMajor();

	/**
	 * Minor version number.
	 * 
	 * @return minor version number
	 */
	public int getMinor();

	/**
	 * Point version number.
	 * 
	 * @return point version number
	 */
	public int getPoint();

}