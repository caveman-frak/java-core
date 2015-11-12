package uk.co.bluegecko.core.models.money.exchange;


import java.util.Currency;

import uk.co.bluegecko.core.models.money.CurrencyException;


/**
 * Provider for getting exchange rate for any supported currency pair. Triangulates the rate against the base currency.
 *
 * @author tim
 *
 */
public interface TriangulatedExchangeRate
{

	/**
	 * The base currency exchange rate provider.
	 *
	 * @return currency exchange rate provider
	 */
	CurrencyExchangeRate getCurrencyExchangeRate();

	/**
	 * The reciprocal exchange rate provider.
	 *
	 * @return reciprocal exchange rate provider
	 */
	ReciprocalExchangeRate getReciprocalExchangeRate();

	/**
	 * Calculate the exchange rate using the base currency and then triangulation.
	 * First get the exchange rates for the two currencies, invert the second rate and then triangulate.
	 *
	 * @param from
	 *            first currency
	 * @param to
	 *            second currency
	 * @return the exchange rate
	 * @throws CurrencyException
	 *             thrown if the requested currencies is not supported
	 */
	ExchangeRate getExchangeRate( final Currency from, final Currency to ) throws CurrencyException;

}
