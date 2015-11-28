/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


import uk.co.bluegecko.core.model.Key.AbstractKeyBase;


/**
 * A typed lookup key for a setting.
 *
 * @param <E>
 *            type of setting value
 */
public class TypedKey< E > extends AbstractKeyBase< TypedKey< E >> implements Typed< E >
{

	private static final long serialVersionUID = -3592813756644358325L;

	private final Class< E > type;

	protected TypedKey( final String name, final Class< E > type )
	{
		super( name );
		this.type = type;
	}

	@Override
	public Class< E > type()
	{
		return type;
	}

	/**
	 * Implementation of a {@link TypedKey} with default value.
	 *
	 * @param <E>
	 *            type of value to represent
	 */
	public static final class DefaultedKey< E > extends TypedKey< E > implements Defaulted< E >
	{

		private static final long serialVersionUID = -601508442702542478L;

		private final E value;

		protected DefaultedKey( final String name, final Class< E > type, final E value )
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
		return new TypedKey<>( name, type );
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
		return new DefaultedKey<>( name, type, value );
	}

}
