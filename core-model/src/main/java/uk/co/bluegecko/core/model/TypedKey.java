/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


/**
 * A typed lookup key for a setting.
 *
 * @param <E>
 *            type of setting value
 */
public interface TypedKey< E > extends Key, Typed< E >
{

	/**
	 * Implementation of a {@link TypedKey}
	 *
	 * @param <E>
	 *            type of value to represent
	 */
	public class TypedKeyBase< E > extends KeyBase implements TypedKey< E >
	{

		private final Class< E > type;

		protected TypedKeyBase( final String name, final Class< E > type )
		{
			super( name );
			this.type = type;
		}

		@Override
		public Class< E > type()
		{
			return type;
		}
	}

	/**
	 * Implementation of a {@link TypedKey}
	 *
	 * @param <E>
	 *            type of value to represent
	 */
	public class KeyDefaultBase< E > extends TypedKeyBase< E > implements TypedKey< E >, Defaulted< E >
	{

		private final E value;

		protected KeyDefaultBase( final String name, final Class< E > type, final E value )
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
	 * Create a new named settings constant.
	 *
	 * @param <E>
	 *            type of the setting value
	 * @param name
	 *            the lookup key of the setting
	 * @param type
	 *            type of the value for the setting
	 * @return a new key
	 */
	public static < E > TypedKey< E > key( final String name, final Class< E > type )
	{
		return new TypedKeyBase<>( name, type );
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
	 * @return a new key with a default value
	 */
	public static < E > TypedKey< E > key( final String name, final Class< E > type, final E value )
	{
		return new KeyDefaultBase<>( name, type, value );
	}

}
