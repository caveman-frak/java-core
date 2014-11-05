/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


/**
 * A typed lookup key for a setting.
 *
 * @param <E>
 *            type of setting value
 */
public interface Setting< E >
{

	/**
	 * @param <E>
	 */
	class SettingBase< E > implements Setting< E >
	{

		private final String name;
		private final Class< E > type;

		protected SettingBase( final String name, final Class< E > type )
		{
			this.name = name;
			this.type = type;
		}

		@Override
		public String name()
		{
			return name;
		}

		@Override
		public Class< E > type()
		{
			return type;
		}
	}

	/**
	 * Return the lookup key for the setting.
	 *
	 * @return the lookup key for the setting
	 */
	public String name();

	/**
	 * The type of the value for the setting.
	 *
	 * @return the type of the value
	 */
	public Class< E > type();

	/**
	 * @param name
	 *            the lookup key of the setting
	 * @param type
	 *            type of the value for the setting
	 * @return a new setting
	 */
	public static < E > Setting< E > setting( final String name, final Class< E > type )
	{
		return new SettingBase<>( name, type );
	}

}
