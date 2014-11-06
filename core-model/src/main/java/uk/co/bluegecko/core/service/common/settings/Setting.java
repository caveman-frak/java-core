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
	 * Implementation of a {@link Setting}
	 *
	 * @param <E>
	 *            type of value to represent
	 */
	public class SettingBase< E > implements Setting< E >
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
	 * Implementation of a {@link Setting}
	 *
	 * @param <E>
	 *            type of value to represent
	 */
	public class SettingDefaultBase< E > extends SettingBase< E > implements Setting< E >, Defaulted< E >
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
	 * Create a new named settings constant.
	 * 
	 * @param <E>
	 *            type of the setting value
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

	/**
	 * Create a new named settings constant with a default value.
	 * 
	 * @param <E>
	 *            type of the setting value
	 * @param name
	 *            the lookup key of the setting
	 * @param type
	 *            type of the value for the setting
	 * @param value
	 *            default value of the setting
	 * @return a new setting with a default value
	 */
	public static < E > Setting< E > setting( final String name, final Class< E > type, final E value )
	{
		return new SettingDefaultBase<>( name, type, value );
	}

}
