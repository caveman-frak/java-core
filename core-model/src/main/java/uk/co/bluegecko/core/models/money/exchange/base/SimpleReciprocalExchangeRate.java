/**
 *
 */
package uk.co.bluegecko.core.models.money.exchange.base;


import java.math.BigDecimal;

import uk.co.bluegecko.core.models.money.exchange.ExchangeRate;
import uk.co.bluegecko.core.models.money.exchange.ReciprocalExchangeRate;


/**
 * Calculate the reciprocal exchange rate by inverting the rate.
 * 
 * @author tim
 *
 */
public class SimpleReciprocalExchangeRate implements ReciprocalExchangeRate
{

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.models.money.exchange.ReciprocalExchangeRate#getExchangeRate(uk.co.bluegecko.core.models.money.exchange.ExchangeRate)
	 */
	@Override
	public ExchangeRate getExchangeRate( final ExchangeRate exchangeRate )
	{
		return new SimpleExchangeRate( exchangeRate.to(), exchangeRate.from(), BigDecimal.ONE.divide( exchangeRate
				.rate() ) );
	}

}
