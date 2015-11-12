package uk.co.bluegecko.core.models.money.exchange;


/**
 * Exchange Rate provider, creating reciprocal exchange rate.
 *
 * @author tim
 *
 */
public interface ReciprocalExchangeRate
{

	/**
	 * Create reciprocal exchange rate.
	 * 
	 * @param exchangeRate
	 *            base rate
	 * @return reciprocal rate
	 */
	ExchangeRate getExchangeRate( final ExchangeRate exchangeRate );

}
