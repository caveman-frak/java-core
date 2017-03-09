package uk.co.bluegecko.core.models.money.exchange.base;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.models.money.Money;
import uk.co.bluegecko.core.models.money.exchange.ExchangeRate;


public class SimpleExchangeRateTest
{

	private final Currency gbp = Currency.getInstance( "GBP" );
	private final Currency usd = Currency.getInstance( "USD" );
	private final BigDecimal ten = new BigDecimal( "10" );
	private final ExchangeRate gbp2Usd = new SimpleExchangeRate( gbp, usd, new BigDecimal( "1.6" ) );
	private Money gbp10;

	@Before
	public void setUp() throws Exception
	{
		gbp10 = new Money( ten, gbp );
	}

	@Test
	public void testCreateGBP2USD()
	{
		assertThat( gbp2Usd.from(), is( gbp ) );
		assertThat( gbp2Usd.to(), is( usd ) );
		assertThat( gbp2Usd.rate(), is( new BigDecimal( "1.6" ) ) );
	}

	@Test
	public void testConvertGBP2JPY()
	{
		final Money result = gbp2Usd.convert( gbp10 );

		assertThat( result.currency(), is( usd ) );
		assertThat( result.amount(), is( new BigDecimal( "16.00" ) ) );
	}

}
