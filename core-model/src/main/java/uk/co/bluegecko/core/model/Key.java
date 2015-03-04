/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


/**
 * A lookup key for a setting.
 */
public interface Key
{

	/**
	 * Return the lookup key for the setting.
	 *
	 * @return the lookup key for the setting
	 */
	public String name();

	/**
	 * Implementation of a {@link Key}
	 */
	public class KeyBase implements Key
	{

		private final String name;

		protected KeyBase( final String name )
		{
			this.name = name;
		}

		@Override
		public String name()
		{
			return name;
		}
	}

	/**
	 * Create a new named settings constant.
	 *
	 * @param name
	 *            the lookup key of the setting
	 * @return a new key
	 */
	public static Key key( final String name )
	{
		return new KeyBase( name );
	}

}
