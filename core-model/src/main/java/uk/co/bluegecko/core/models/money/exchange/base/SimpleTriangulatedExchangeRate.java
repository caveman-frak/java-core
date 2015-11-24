package uk.co.bluegecko.core.models.money.exchange.base;


import java.util.Currency;

import uk.co.bluegecko.core.models.money.CurrencyException;
import uk.co.bluegecko.core.models.money.Money;
import uk.co.bluegecko.core.models.money.exchange.CurrencyExchangeRate;
import uk.co.bluegecko.core.models.money.exchange.ExchangeRate;
import uk.co.bluegecko.core.models.money.exchange.ReciprocalExchangeRate;
import uk.co.bluegecko.core.models.money.exchange.TriangulatedExchangeRate;


/**
 * @author tim
 *
 */
public class SimpleTriangulatedExchangeRate implements TriangulatedExchangeRate
{

	private final CurrencyExchangeRate currencyExchangeRate;
	private final ReciprocalExchangeRate reciprocalExchangeRate;

	/**
	 * @param currencyExchangeRate
	 *            the provider for exchange rates
	 * @param reciprocalExchangeRate
	 *            the calculator for reciprocal rates
	 */
	public SimpleTriangulatedExchangeRate( final CurrencyExchangeRate currencyExchangeRate,
			final ReciprocalExchangeRate reciprocalExchangeRate )
	{
		this.currencyExchangeRate = currencyExchangeRate;
		this.reciprocalExchangeRate = reciprocalExchangeRate;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.exchange.TriangulatedExchangeRate#getCurrencyExchangeRate()
	 */
	@Override
	public CurrencyExchangeRate getCurrencyExchangeRate()
	{
		return currencyExchangeRate;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.exchange.TriangulatedExchangeRate#getReciprocalExchangeRate()
	 */
	@Override
	public ReciprocalExchangeRate getReciprocalExchangeRate()
	{
		return reciprocalExchangeRate;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.exchange.TriangulatedExchangeRate#getExchangeRate(java.util.Currency, java.util.Currency)
	 */
	@Override
	public ExchangeRate getExchangeRate( final Currency from, final Currency to ) throws CurrencyException
	{
		ExchangeRate fromRate = currencyExchangeRate.getExchangeRate( from );
		final ExchangeRate toRate = currencyExchangeRate.getExchangeRate( to );

		if ( !fromRate.to().equals( toRate.from() ) )
		{
			fromRate = reciprocalExchangeRate.getExchangeRate( fromRate );
		}
		if ( !fromRate.to().equals( toRate.from() ) )
		{
			throw new CurrencyException( Money.Log.CURRENCY_MISMATCH, fromRate.to(), toRate.from() );
		}

		return new SimpleExchangeRate( from, to, fromRate.rate().multiply( toRate.rate() ) );
	}
}
