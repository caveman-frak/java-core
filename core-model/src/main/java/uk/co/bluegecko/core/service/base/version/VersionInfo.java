package uk.co.bluegecko.core.service.base.version;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Define a version.
 *
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
@Inherited
public @interface VersionInfo
{

	/**
	 * Major version number.
	 *
	 * @return major version number
	 */
	int major();

	/**
	 * Minor version number.
	 *
	 * @return minor version number
	 */
	int minor();

	/**
	 * Point version number.
	 *
	 * @return point version number
	 */
	int point();

}
