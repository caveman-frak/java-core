package uk.co.bluegecko.core.models.money.exchange;


import java.util.Currency;

import uk.co.bluegecko.core.models.money.CurrencyException;


/**
 * Exchange Rate provider, based off a single base currency.
 *
 * @author tim
 *
 */
public interface CurrencyExchangeRate
{

	/**
	 * The base currency for the provider.
	 *
	 * @return base currency
	 */
	public Currency getBaseCurrency();

	/**
	 * ExchangeRate producer.
	 *
	 * @param currency
	 *            the "to" currency
	 * @return the exchange rate
	 * @throws CurrencyException
	 *             thrown if the requested currency is not supported
	 */
	public ExchangeRate getExchangeRate( final Currency currency ) throws CurrencyException;

}
