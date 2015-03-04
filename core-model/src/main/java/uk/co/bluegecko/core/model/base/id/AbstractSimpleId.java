/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.id;


import uk.co.bluegecko.core.lang.CompareToBuilder;
import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.lang.HashCodeBuilder;
import uk.co.bluegecko.core.lang.ToStringBuilder;
import uk.co.bluegecko.core.model.id.SimpleId;


/**
 * @param <T>
 *            type of identifier
 */
public abstract class AbstractSimpleId< T extends Comparable< T > > implements SimpleId< T >
{

	private static final long serialVersionUID = 4684468142808080507L;

	private final T value;

	protected AbstractSimpleId( final T value )
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.id.SimpleId#getValue()
	 */
	@Override
	public final T getValue()
	{
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public final int compareTo( final SimpleId< T > that )
	{
		return new CompareToBuilder().append( getValue(), that.getValue() ).toComparison();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode()
	{
		return new HashCodeBuilder().append( value ).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals( final Object obj )
	{
		final EqualsBuilder< SimpleId< ? > > builder = new EqualsBuilder<>( this, obj );
		if ( builder.isResolved() )
			return builder.isSame();
		final SimpleId< ? > that = builder.getRhs();
		builder.append( getValue(), that.getValue() );
		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString()
	{
		return new ToStringBuilder( this ).append( "value", getValue() ).build();
	}

}
