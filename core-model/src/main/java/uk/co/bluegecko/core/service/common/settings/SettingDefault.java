/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.common.settings;


/**
 * A typed lookup key for a setting, with a default value.
 *
 * @param <E>
 *            type of setting value and default
 */
public interface SettingDefault< E > extends Setting< E >
{

	/**
	 * @param <E>
	 */
	class SettingDefaultBase< E > extends SettingBase< E > implements SettingDefault< E >
	{

		private final E value;

		protected SettingDefaultBase( final String name, final Class< E > type, final E value )
		{
			super( name, type );

			this.value = value;
		}

		@Override
		public E defaultValue()
		{
			return value;
		}
	}

	/**
	 * Return the default value for a setting.
	 *
	 * @return the default value for a setting
	 */
	public E defaultValue();

	/**
	 * @param name
	 *            the lookup key of the setting
	 * @param type
	 *            type of the value for the setting
	 * @param value
	 *            default value of the setting
	 * @return a new setting with a default value
	 */
	public static < E > SettingDefault< E > setting( final String name, final Class< E > type, final E value )
	{
		return new SettingDefaultBase<>( name, type, value );
	}

}
