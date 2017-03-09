package uk.co.bluegecko.core.models.money;


import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.caught;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;


public class MoneyTest
{

	private final Currency gbp = Currency.getInstance( "GBP" );
	private final Currency jpy = Currency.getInstance( "JPY" );
	private final BigDecimal four = new BigDecimal( "4" );
	private final BigDecimal ten = new BigDecimal( "10" );
	private final BigDecimal twenty = new BigDecimal( "20" );
	private Money gbp10, gbp20, jpy10, jpy20;

	@Before
	public void setUp() throws Exception
	{
		gbp10 = new Money( ten, gbp );
		gbp20 = new Money( twenty, gbp );
		jpy10 = new Money( ten, jpy );
		jpy20 = new Money( twenty, jpy );
	}

	@Test
	public void testCreateGBP()
	{
		assertThat( gbp10.currency(), is( gbp ) );
		assertThat( gbp10.amount(), is( new BigDecimal( "10.00" ) ) );
		assertThat( gbp10.amount()
				.scale(), is( 2 ) );
	}

	@Test
	public void testCreateJPY()
	{
		assertThat( jpy10.currency(), is( jpy ) );
		assertThat( jpy10.amount(), is( new BigDecimal( "10" ) ) );
		assertThat( jpy10.amount()
				.scale(), is( 0 ) );
	}

	@Test
	public void testAddGBP()
	{
		final Money result = gbp20.add( gbp10 );

		assertThat( result.currency(), is( gbp ) );
		assertThat( result.amount(), is( new BigDecimal( "30.00" ) ) );
	}

	@Test
	public void testAddJPY()
	{
		final Money result = jpy20.add( jpy10 );

		assertThat( result.currency(), is( jpy ) );
		assertThat( result.amount(), is( new BigDecimal( "30" ) ) );
	}

	@Test
	public void testAddGBPAndJPY()
	{
		capture( () -> gbp20.add( jpy10 ) );
		assertThat( caught(), is( instanceOf( CurrencyException.class ) ) );
	}

	@Test
	public void testSubtractGBP()
	{
		final Money result = gbp20.subtract( gbp10 );

		assertThat( result.currency(), is( gbp ) );
		assertThat( result.amount(), is( new BigDecimal( "10.00" ) ) );
	}

	@Test
	public void testSubtractJPY()
	{
		final Money result = jpy20.subtract( jpy10 );

		assertThat( result.currency(), is( jpy ) );
		assertThat( result.amount(), is( new BigDecimal( "10" ) ) );
	}

	@Test
	public void testSubtractGBPAndJPY()
	{
		capture( () -> gbp20.subtract( jpy10 ) );
		assertThat( caught(), is( instanceOf( CurrencyException.class ) ) );
	}

	@Test
	public void testMultiplyGBP()
	{
		final Money result = gbp10.multiply( four );

		assertThat( result.currency(), is( gbp ) );
		assertThat( result.amount(), is( new BigDecimal( "40.00" ) ) );
	}

	@Test
	public void testMultiplyJPY()
	{
		final Money result = jpy10.multiply( four );

		assertThat( result.currency(), is( jpy ) );
		assertThat( result.amount(), is( new BigDecimal( "40" ) ) );
	}

	@Test
	public void testDivideGBP()
	{
		final Money result = gbp10.divide( four );

		assertThat( result.currency(), is( gbp ) );
		assertThat( result.amount(), is( new BigDecimal( "2.50" ) ) );
	}

	@Test
	public void testDivideJPY()
	{
		final Money result = jpy10.divide( four );

		assertThat( result.currency(), is( jpy ) );
		assertThat( result.amount(), is( new BigDecimal( "2" ) ) );
	}

}
