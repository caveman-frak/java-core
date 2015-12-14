package uk.co.bluegecko.core.validation;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.debug.AsString.asString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.model.Messages;
import uk.co.bluegecko.core.model.Messages.Severity;
import uk.co.bluegecko.core.model.base.MessagesBase;


@SuppressWarnings( "javadoc" )
public class AbstractValidatorTest
{

	private static final String FIELDNAME = "testField";

	private AbstractValidator validator;
	private Messages messages;

	@Before
	public void setUp() throws Exception
	{
		validator = new TestValidator();
		messages = new MessagesBase();
	}

	@Test
	public final void testNotNullPass()
	{
		final Object test = Boolean.FALSE;
		validator.notNull( messages, Severity.WARN, FIELDNAME, test );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNotNullFail()
	{
		final Object test = null;
		validator.notNull( messages, Severity.WARN, FIELDNAME, test );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ), contains( "NOT_NULL[testField]" ) );
	}

	@Test
	public final void testLengthMaxPass()
	{
		final String test = "12345";
		validator.lengthMax( messages, Severity.WARN, FIELDNAME, test, 5 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testLengthMaxFail()
	{
		final String test = "123456";
		validator.lengthMax( messages, Severity.WARN, FIELDNAME, test, 5 );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ), contains( "LENGTH_MAX[testField,123456,5]" ) );
	}

	@Test
	public final void testLengthMaxNull()
	{
		final String test = null;
		validator.lengthMax( messages, Severity.WARN, FIELDNAME, test, 5 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testLengthMinPass()
	{
		final String test = "12345";
		validator.lengthMin( messages, Severity.WARN, FIELDNAME, test, 5 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testLengthMinFail()
	{
		final String test = "1234";
		validator.lengthMin( messages, Severity.WARN, FIELDNAME, test, 5 );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ), contains( "LENGTH_MIN[testField,1234,5]" ) );
	}

	@Test
	public final void testLengthMinNull()
	{
		final String test = null;
		validator.lengthMin( messages, Severity.WARN, FIELDNAME, test, 5 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testLengthBetweenPassMin()
	{
		final String test = "12345";
		validator.lengthBetween( messages, Severity.WARN, FIELDNAME, test, 5, 10 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testLengthBetweenPassMax()
	{
		final String test = "1234567890";
		validator.lengthBetween( messages, Severity.WARN, FIELDNAME, test, 5, 10 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testLengthBetweenFailMin()
	{
		final String test = "1234";
		validator.lengthBetween( messages, Severity.WARN, FIELDNAME, test, 5, 10 );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "LENGTH_BETWEEN[testField,1234,5,10]" ) );
	}

	@Test
	public final void testLengthBetweenFailMax()
	{
		final String test = "1234567890A";
		validator.lengthBetween( messages, Severity.WARN, FIELDNAME, test, 5, 10 );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "LENGTH_BETWEEN[testField,1234567890A,5,10]" ) );
	}

	@Test
	public final void testLengthBlank()
	{
		final String test = "";
		validator.lengthBetween( messages, Severity.WARN, FIELDNAME, test, 5, 10 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testRegexPass()
	{
		final String test = "ABCE456XY";
		validator.regex( messages, Severity.WARN, FIELDNAME, test, "[A-Z]{2,4}\\d{3}[A-Z]{2,4}" );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testRegexFail()
	{
		final String test = "AB45DE";
		validator.regex( messages, Severity.WARN, FIELDNAME, test, "[A-Z]{2,4}\\d{3}[A-Z]{2,4}" );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "REGEX[testField,AB45DE,[A-Z]{2,4}\\d{3}[A-Z]{2,4}]" ) );
	}

	@Test
	public final void testRegexBlank()
	{
		final String test = "";
		validator.regex( messages, Severity.WARN, FIELDNAME, test, "[A-Z]{2,4}\\d{3}[A-Z]{2,4}" );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberMaxPass()
	{
		final BigInteger test = BigInteger.valueOf( 5L );
		validator.numberMax( messages, Severity.WARN, FIELDNAME, test, BigInteger.valueOf( 10L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberMaxFail()
	{
		final BigInteger test = BigInteger.valueOf( 11L );
		validator.numberMax( messages, Severity.WARN, FIELDNAME, test, BigInteger.valueOf( 10L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ), contains( "NUMBER_MAX[testField,11,10]" ) );
	}

	@Test
	public final void testNumberMaxNull()
	{
		final BigInteger test = null;
		validator.numberMax( messages, Severity.WARN, FIELDNAME, test, BigInteger.valueOf( 10L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberMinPass()
	{
		final BigInteger test = BigInteger.valueOf( 5L );
		validator.numberMin( messages, Severity.WARN, FIELDNAME, test, BigInteger.valueOf( 5L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberMinFail()
	{
		final BigInteger test = BigInteger.valueOf( 4L );
		validator.numberMin( messages, Severity.WARN, FIELDNAME, test, BigInteger.valueOf( 5L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ), contains( "NUMBER_MIN[testField,4,5]" ) );
	}

	@Test
	public final void testNumberMinNull()
	{
		final BigInteger test = null;
		validator.numberMin( messages, Severity.WARN, FIELDNAME, test, BigInteger.valueOf( 5L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberBetweenPassMin()
	{
		final Float test = 5F;
		validator.numberBetween( messages, Severity.WARN, FIELDNAME, test, 5F, 10F );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberBetweenPassMax()
	{
		final Integer test = 10;
		validator.numberBetween( messages, Severity.WARN, FIELDNAME, test, 5, 10 );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testNumberBetweenFailMin()
	{
		final Double test = 4D;
		validator.numberBetween( messages, Severity.WARN, FIELDNAME, test, 5D, 10D );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "NUMBER_BETWEEN[testField,4.0,5.0,10.0]" ) );
	}

	@Test
	public final void testNumberBetweenFailMax()
	{
		final BigDecimal test = BigDecimal.valueOf( 11L );
		validator.numberBetween( messages, Severity.WARN, FIELDNAME, test, BigDecimal.valueOf( 5L ),
				BigDecimal.valueOf( 10L ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ), contains( "NUMBER_BETWEEN[testField,11,5,10]" ) );
	}

	@Test
	public final void testNumberBetweenNull()
	{
		final Long test = null;
		validator.numberBetween( messages, Severity.WARN, FIELDNAME, test, 5L, 10L );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateMaxPass()
	{
		final LocalDate test = LocalDate.of( 2010, Month.SEPTEMBER, 1 );
		validator.dateMax( messages, Severity.WARN, FIELDNAME, test, LocalDate.of( 2010, Month.SEPTEMBER, 1 ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateMaxFail()
	{
		final LocalDate test = LocalDate.of( 2010, Month.SEPTEMBER, 2 );
		validator.dateMax( messages, Severity.WARN, FIELDNAME, test, LocalDate.of( 2010, Month.SEPTEMBER, 1 ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "DATE_MAX[testField,2010-09-02,2010-09-01]" ) );
	}

	@Test
	public final void testDateMaxNull()
	{
		final LocalDate test = null;
		validator.dateMax( messages, Severity.WARN, FIELDNAME, test, LocalDate.of( 2010, Month.SEPTEMBER, 1 ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateMinPass()
	{
		final LocalDateTime test = LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 0 );
		validator.dateMin( messages, Severity.WARN, FIELDNAME, test,
				LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 0 ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateMinFail()
	{
		final LocalDateTime test = LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 11, 59 );
		validator.dateMin( messages, Severity.WARN, FIELDNAME, test,
				LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 0 ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "DATE_MIN[testField,2010-09-01T11:59,2010-09-01T12:00]" ) );
	}

	@Test
	public final void testDateMinNull()
	{
		final LocalDateTime test = null;
		validator.dateMin( messages, Severity.WARN, FIELDNAME, test,
				LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 0 ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateBetweenPassMin()
	{
		final ZonedDateTime test = ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 00 ),
				ZoneOffset.UTC );
		validator.dateBetween( messages, Severity.WARN, FIELDNAME, test,
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 00 ), ZoneOffset.UTC ),
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 59 ), ZoneOffset.UTC ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateBetweenPassMax()
	{
		final ZonedDateTime test = ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 59 ),
				ZoneOffset.UTC );
		validator.dateBetween( messages, Severity.WARN, FIELDNAME, test,
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 00 ), ZoneOffset.UTC ),
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 59 ), ZoneOffset.UTC ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	@Test
	public final void testDateBetweenFailMin()
	{
		final ZonedDateTime test = ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 11, 59 ),
				ZoneOffset.UTC );
		validator.dateBetween( messages, Severity.WARN, FIELDNAME, test,
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 00 ), ZoneOffset.UTC ),
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 59 ), ZoneOffset.UTC ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "DATE_BETWEEN[testField,2010-09-01T11:59Z,2010-09-01T12:00Z,2010-09-01T12:59Z]" ) );
	}

	@Test
	public final void testDateBetweenFailMax()
	{
		final ZonedDateTime test = ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 13, 00 ),
				ZoneOffset.UTC );
		validator.dateBetween( messages, Severity.WARN, FIELDNAME, test,
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 00 ), ZoneOffset.UTC ),
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 59 ), ZoneOffset.UTC ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( true ) );
		assertThat( messages.hasMessages( Severity.WARN, FIELDNAME ), is( true ) );
		assertThat( messages.getMessages( Severity.WARN, FIELDNAME ),
				contains( "DATE_BETWEEN[testField,2010-09-01T13:00Z,2010-09-01T12:00Z,2010-09-01T12:59Z]" ) );
	}

	@Test
	public final void testDateBetweenNull()
	{
		final ZonedDateTime test = null;
		validator.dateBetween( messages, Severity.WARN, FIELDNAME, test,
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 00 ), ZoneOffset.UTC ),
				ZonedDateTime.of( LocalDateTime.of( 2010, Month.SEPTEMBER, 1, 12, 59 ), ZoneOffset.UTC ) );

		assertThat( messages.hasMessages( Severity.WARN ), is( false ) );
	}

	private static final class TestValidator extends AbstractValidator
	{

		public TestValidator()
		{
			super();
		}

		@Override
		protected Locale getLocale()
		{
			return Locale.ENGLISH;
		}

		@Override
		protected String getBundle()
		{
			return TestValidator.class.getName();
		}

		@Override
		protected String getMessage( final Locale locale, final String bundle, final Object key, final Object... args )
		{
			return key + asString( args );
		}

	}

}
