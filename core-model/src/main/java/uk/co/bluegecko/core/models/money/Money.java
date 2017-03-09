/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.models.money;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
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

	@BaseName( "uk.co.bluegecko.core.models.money.Money$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		CURRENCY_MISMATCH, UNRECOGNISED_CURRENCY
	}

	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

	private final BigDecimal amount;
	private final Currency currency;

	/**
	 * Create a new instance of Money with an amount and currency.
	 *
	 * @param amount
	 *            the amount
	 * @param currency
	 *            the currency
	 */
	public Money( final BigDecimal amount, final Currency currency )
	{
		super();

		this.currency = currency;

		if ( currency.getDefaultFractionDigits() != -1 )
		{
			this.amount = amount.setScale( currency.getDefaultFractionDigits(), ROUNDING_MODE );
		}
		else
		{
			this.amount = amount;
		}
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
	 * @see uk.co.bluegecko.core.models.quantity.Quantity#currency()
	 */
	@Override
	public Currency unit()
	{
		return currency;
	}

	/**
	 * The currency of the money.
	 *
	 * @return the currency
	 */
	public Currency currency()
	{
		return unit();
	}

	/**
	 * Add the amount of both monies together.
	 * They must be of the same currency, or a CurrencyMismatchException is thrown.
	 *
	 * @param money
	 *            the money to add
	 * @return a new money
	 * @throws CurrencyException
	 *             thrown if both monies are not of the same currency
	 */
	public Money add( final Money money ) throws CurrencyException
	{
		checkCurrency( money );
		return new Money( amount.add( money.amount() ), currency );
	}

	/**
	 * Subtract the amount one money from this money.
	 * They must be of the same currency, or a CurrencyMismatchException is thrown.
	 *
	 * @param money
	 *            the money to subtract
	 * @return a new money
	 * @throws CurrencyException
	 *             thrown if both monies are not of the same currency
	 */
	public Money subtract( final Money money ) throws CurrencyException
	{
		checkCurrency( money );
		return new Money( amount.subtract( money.amount() ), currency );
	}

	/**
	 * Multiply this money by an amount.
	 *
	 * @param multiplicand
	 *            the amount to multiply by.
	 * @return a new money
	 */
	public Money multiply( final BigDecimal multiplicand )
	{
		return new Money( amount.multiply( multiplicand ), currency );
	}

	/**
	 * Divide this money by an amount.
	 *
	 * @param divisor
	 *            the amount to divide by.
	 * @return a new money
	 */
	public Money divide( final BigDecimal divisor )
	{
		return new Money( amount.divide( divisor ), currency );
	}

	private void checkCurrency( final Money money ) throws CurrencyException
	{
		if ( !isSameCurrency( money ) )
		{
			throw new CurrencyException( Log.CURRENCY_MISMATCH, currency, money.currency() );
		}
	}

	protected boolean isSameCurrency( final Money money )
	{
		return money != null && currency.equals( money.currency );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( final Money that )
	{
		return new CompareToBuilder().append( amount, that.amount )
				.append( currency, that.currency )
				.toComparison();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append( amount )
				.append( currency )
				.toHashCode();
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
		{
			return builder.isSame();
		}
		final Money that = builder.getRhs();
		return builder.append( amount, that.amount )
				.append( currency, that.currency )
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder( this ).append( "amount", amount )
				.append( "ccy", currency )
				.toString();
	}

}
