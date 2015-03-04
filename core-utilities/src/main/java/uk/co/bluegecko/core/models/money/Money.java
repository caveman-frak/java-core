/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.money;


import java.math.BigDecimal;
import java.util.Currency;

import uk.co.bluegecko.core.lang.CompareToBuilder;
import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.lang.HashCodeBuilder;
import uk.co.bluegecko.core.lang.ToStringBuilder;
import uk.co.bluegecko.core.models.quantity.Quantity;


/**
 * Representation of an quantity of money in a given currency.
 */
public class Money implements Quantity< BigDecimal, Currency >, Comparable< Money >
{

	private final BigDecimal amount;
	private final Currency unit;

	/**
	 * Create a new instance of Money with an amount and currency.
	 *
	 * @param amount
	 *            the amount
	 * @param unit
	 *            the currency
	 */
	public Money( final BigDecimal amount, final Currency unit )
	{
		super();

		this.amount = amount;
		this.unit = unit;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.quantity.Quantity#amount()
	 */
	@Override
	public BigDecimal amount()
	{
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.quantity.Quantity#unit()
	 */
	@Override
	public Currency unit()
	{
		return unit;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( final Money that )
	{
		return new CompareToBuilder().append( amount, that.amount ).append( unit, that.unit ).toComparison();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append( amount ).append( unit ).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( final Object obj )
	{
		final EqualsBuilder< Money > builder = new EqualsBuilder<>( this, obj );
		if ( builder.isResolved() )
			return builder.isSame();
		final Money that = builder.getRhs();
		return builder.append( amount, that.amount ).append( unit, that.unit ).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder( this ).append( "amount", amount ).append( "ccy", unit ).toString();
	}

}
