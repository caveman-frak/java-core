/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.id;


import uk.co.bluegecko.core.lang.CompareToBuilder;
import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.lang.HashCodeBuilder;
import uk.co.bluegecko.core.lang.ToStringBuilder;
import uk.co.bluegecko.core.model.id.CompoundId;


/**
 * @param <P>
 *            type of primary identifier
 * @param <S>
 *            type of secondary identifier
 */
public abstract class AbstractCompoundId< P extends Comparable< P >, S extends Comparable< S >> implements
		CompoundId< P, S >
{

	private static final long serialVersionUID = -7858133557660394749L;

	private final P primary;
	private final S secondary;

	protected AbstractCompoundId( final P primary, final S secondary )
	{
		this.primary = primary;
		this.secondary = secondary;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.id.CompoundId#getPrimary()
	 */
	@Override
	public P getPrimary()
	{
		return primary;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.id.CompoundId#getSecondary()
	 */
	@Override
	public S getSecondary()
	{
		return secondary;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public final int compareTo( final CompoundId< P, S > that )
	{
		return new CompareToBuilder().append( getPrimary(), that.getPrimary() )
				.append( getSecondary(), that.getSecondary() ).toComparison();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode()
	{
		return new HashCodeBuilder().append( primary ).append( secondary ).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals( final Object obj )
	{
		final EqualsBuilder< AbstractCompoundId< ?, ? > > builder = new EqualsBuilder<>( this, obj );
		if ( builder.isResolved() )
			return builder.isSame();
		final AbstractCompoundId< ?, ? > that = builder.getRhs();
		builder.append( getPrimary(), that.getPrimary() );
		builder.append( getSecondary(), that.getSecondary() );
		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString()
	{
		return new ToStringBuilder( this ).append( "primary", getPrimary() ).append( "secondary", getSecondary() )
				.build();
	}

}
