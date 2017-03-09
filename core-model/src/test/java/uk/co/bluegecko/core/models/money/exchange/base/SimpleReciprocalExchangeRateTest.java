package uk.co.bluegecko.core.models.money.exchange.base;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.models.money.exchange.ExchangeRate;
import uk.co.bluegecko.core.models.money.exchange.ReciprocalExchangeRate;


public class SimpleReciprocalExchangeRateTest
{

	private final Currency gbp = Currency.getInstance( "GBP" );
	private final Currency usd = Currency.getInstance( "USD" );
	private final ExchangeRate gbp2Usd = new SimpleExchangeRate( gbp, usd, new BigDecimal( "1.6" ) );
	private ReciprocalExchangeRate reciprocalExchangeRate;

	@Before
	public void setup()
	{
		reciprocalExchangeRate = new SimpleReciprocalExchangeRate();
	}

	@Test
	public void testInvert()
	{
		final ExchangeRate result = reciprocalExchangeRate.getExchangeRate( gbp2Usd );

		assertThat( result.from(), is( usd ) );
		assertThat( result.to(), is( gbp ) );
		assertThat( result.rate(), is( new BigDecimal( "0.625" ) ) );
	}

}
