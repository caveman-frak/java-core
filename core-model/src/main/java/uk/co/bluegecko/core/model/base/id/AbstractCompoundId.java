/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
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
	public P primary()
	{
		return primary;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.id.CompoundId#getSecondary()
	 */
	@Override
	public S secondary()
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
		return new CompareToBuilder().append( primary(), that.primary() )
				.append( secondary(), that.secondary() ).toComparison();
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
		builder.append( primary(), that.primary() );
		builder.append( secondary(), that.secondary() );
		return builder.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString()
	{
		return new ToStringBuilder( this ).append( "primary", primary() ).append( "secondary", secondary() )
				.build();
	}

}
