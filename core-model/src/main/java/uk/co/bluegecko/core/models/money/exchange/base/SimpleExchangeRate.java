package uk.co.bluegecko.core.models.money.exchange.base;


import java.math.BigDecimal;
import java.util.Currency;

import uk.co.bluegecko.core.lang.CompareToBuilder;
import uk.co.bluegecko.core.lang.EqualsBuilder;
import uk.co.bluegecko.core.lang.HashCodeBuilder;
import uk.co.bluegecko.core.lang.ToStringBuilder;
import uk.co.bluegecko.core.models.money.CurrencyException;
import uk.co.bluegecko.core.models.money.Money;
import uk.co.bluegecko.core.models.money.exchange.ExchangeRate;


/**
 * Concrete ExchangeRate, using simple multiplication of amount times rate.
 *
 * @author tim
 *
 */
public class SimpleExchangeRate implements ExchangeRate
{

	private final Currency from;
	private final Currency to;
	private final BigDecimal rate;

	protected SimpleExchangeRate( final Currency from, final Currency to, final BigDecimal rate )
	{
		this.from = from;
		this.to = to;
		this.rate = rate;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.ExchangeRate#from()
	 */
	@Override
	public Currency from()
	{
		return from;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.ExchangeRate#to()
	 */
	@Override
	public Currency to()
	{
		return to;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.ExchangeRate#rate()
	 */
	@Override
	public BigDecimal rate()
	{
		return rate;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.ExchangeRate#convert(uk.co.bluegecko.core.models.money.Money)
	 */
	@Override
	public Money convert( final Money money ) throws CurrencyException
	{
		checkCurrency( money );
		return new Money( money.amount().multiply( rate ), to );
	}

	private void checkCurrency( final Money money ) throws CurrencyException
	{
		if ( !isSameCurrency( money ) )
		{
			throw new CurrencyException( Money.Log.CURRENCY_MISMATCH, from, money.currency() );
		}
	}

	protected boolean isSameCurrency( final Money money )
	{
		return money != null && from.equals( money.currency() );
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo( final ExchangeRate that )
	{
		return new CompareToBuilder().append( from, that.from() ).append( to, that.to() ).append( rate, that.rate() )
				.toComparison();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder().append( from ).append( to ).append( rate ).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( final Object obj )
	{
		final EqualsBuilder< ExchangeRate > builder = new EqualsBuilder<>( this, obj );
		if ( builder.isResolved() )
		{
			return builder.isSame();
		}
		final ExchangeRate that = builder.getRhs();
		return builder.append( from, that.from() ).append( to, that.to() ).append( rate, that.rate() ).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return new ToStringBuilder( this ).append( "from", from ).append( "to", to ).append( "rate", rate ).toString();
	}

}
