/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.key;


import java.io.Serializable;

import uk.co.bluegecko.core.lang.CompareToBuilder;
import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.lang.HashCodeBuilder;
import uk.co.bluegecko.core.lang.ToStringBuilder;


/**
 * A lookup key for a setting.
 *
 * @param <K>
 *            reference to self for comparable
 */
public interface Key< K extends Key< K > > extends Serializable, Comparable< K >
{

	/**
	 * Return the lookup key for the setting.
	 *
	 * @return the lookup key for the setting
	 */
	public String name();

	/**
	 * Abstract implementation of a {@link Key}
	 *
	 * @param <K>
	 *            reference to self for comparable
	 */
	public abstract class AbstractKeyBase< K extends AbstractKeyBase< K > > implements Key< K >
	{

		private static final long serialVersionUID = -6992563050425551428L;

		private final String name;

		protected AbstractKeyBase( final String name )
		{
			this.name = name;
		}

		@Override
		public String name()
		{
			return name;
		}

		@Override
		public int compareTo( final K other )
		{
			return new CompareToBuilder().append( name(), other.name() ).toComparison();
		}

		@Override
		public int hashCode()
		{
			return new HashCodeBuilder().append( name() ).toHashCode();
		}

		@Override
		public boolean equals( final Object other )
		{
			final EqualsBuilder< Key< ? > > equalsBuilder = new EqualsBuilder< >( this, other );
			if ( equalsBuilder.isResolved() )
			{
				return equalsBuilder.isSame();
			}
			final Key< ? > that = ( uk.co.bluegecko.core.model.key.Key< ? > ) other;
			return equalsBuilder.append( name(), that.name() ).isEquals();
		}

		@Override
		public String toString()
		{
			return new ToStringBuilder( this ).append( name() ).toString();
		}

	}

	/**
	 * Implementation of a {@link Key}
	 */
	public class KeyBase extends AbstractKeyBase< KeyBase >
	{

		private static final long serialVersionUID = -7498047143922932282L;

		protected KeyBase( final String name )
		{
			super( name );
		}

	}

	/**
	 * Create a new named settings constant.
	 *
	 * @param name
	 *            the lookup key of the setting
	 * @return a new key
	 */
	public static Key< KeyBase > key( final String name )
	{
		return new KeyBase( name );
	}

}
