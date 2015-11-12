package uk.co.bluegecko.core.models.money.exchange.base;


import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.caught;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.models.money.CurrencyException;
import uk.co.bluegecko.core.models.money.Money.Log;
import uk.co.bluegecko.core.models.money.exchange.CurrencyExchangeRate;
import uk.co.bluegecko.core.models.money.exchange.ExchangeRate;


@SuppressWarnings( "javadoc" )
public class SimpleTriangulatedExchangeRateTest
{

	private final Currency gbp = Currency.getInstance( "GBP" );
	private final Currency jpy = Currency.getInstance( "JPY" );
	private final Currency usd = Currency.getInstance( "USD" );
	private final Currency cad = Currency.getInstance( "CAD" );
	private final ExchangeRate usd2gbp = new SimpleExchangeRate( usd, gbp, new BigDecimal( "0.625" ) );
	private final ExchangeRate usd2jpy = new SimpleExchangeRate( usd, jpy, new BigDecimal( "100" ) );
	private SimpleReciprocalExchangeRate reciprocalExchangeRate;
	private CurrencyExchangeRate currencyExchangeRate;
	private SimpleTriangulatedExchangeRate triangulatedExchangeRate;

	@Before
	public void setUp() throws Exception
	{
		currencyExchangeRate = mock( CurrencyExchangeRate.class );

		reciprocalExchangeRate = new SimpleReciprocalExchangeRate();
		triangulatedExchangeRate = new SimpleTriangulatedExchangeRate( currencyExchangeRate, reciprocalExchangeRate );

		when( currencyExchangeRate.getBaseCurrency() ).thenReturn( usd );
		when( currencyExchangeRate.getExchangeRate( gbp ) ).thenReturn( usd2gbp );
		when( currencyExchangeRate.getExchangeRate( jpy ) ).thenReturn( usd2jpy );
		when( currencyExchangeRate.getExchangeRate( cad ) ).thenThrow(
				new CurrencyException( Log.UNRECOGNISED_CURRENCY, cad ) );
	}

	@Test
	public void testGetCurrencyExchangeRate()
	{
		assertThat( triangulatedExchangeRate.getCurrencyExchangeRate(), is( currencyExchangeRate ) );
	}

	@Test
	public void testGetReciprocalExchangeRate()
	{
		assertThat( triangulatedExchangeRate.getReciprocalExchangeRate(), is( reciprocalExchangeRate ) );
	}

	@Test
	public void testGetExchangeRateJPY2GBP()
	{
		final ExchangeRate result = triangulatedExchangeRate.getExchangeRate( jpy, gbp );

		assertThat( result.from(), is( jpy ) );
		assertThat( result.to(), is( gbp ) );
		assertThat( result.rate(), is( new BigDecimal( "0.00625" ) ) );
	}

	@Test
	public void testGetExchangeRateGBP2JPY()
	{
		final ExchangeRate result = triangulatedExchangeRate.getExchangeRate( gbp, jpy );

		assertThat( result.from(), is( gbp ) );
		assertThat( result.to(), is( jpy ) );
		assertThat( result.rate(), is( new BigDecimal( "160.0" ) ) );
	}

	@Test
	public void testGetExchangeRateGBP2CAD()
	{
		capture( ( ) -> triangulatedExchangeRate.getExchangeRate( gbp, cad ) );

		assertThat( caught(), is( instanceOf( CurrencyException.class ) ) );
	}

}
