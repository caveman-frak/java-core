package uk.co.bluegecko.core.models.money.exchange;


import java.math.BigDecimal;
import java.util.Currency;

import uk.co.bluegecko.core.models.money.CurrencyException;
import uk.co.bluegecko.core.models.money.Money;


/**
 * Exchange Rate for converting money from one currency to another.
 *
 * @author tim
 *
 */
public interface ExchangeRate extends Comparable< ExchangeRate >
{

	/**
	 * The currency of money to convert from.
	 *
	 * @return the from currency
	 */
	public Currency from();

	/**
	 * The currency of money to convert to.
	 *
	 * @return the to currency
	 */
	public Currency to();

	/**
	 * The exchange rate of the conversion.
	 *
	 * @return exchange rate
	 */
	public BigDecimal rate();

	/**
	 * Convert money from the "from" currency to the "to" currency.
	 *
	 * @param money
	 *            the money to conver
	 * @return the converted money
	 * @throws CurrencyException
	 *             thrown if the money does not match the "from" currency
	 */
	public Money convert( final Money money ) throws CurrencyException;

}
