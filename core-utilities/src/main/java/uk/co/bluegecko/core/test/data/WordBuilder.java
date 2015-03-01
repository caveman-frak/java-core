package uk.co.bluegecko.core.test.data;


import java.util.Arrays;


/**
 * Utility methods for converting integers into words and words to integers.
 */
public final class WordBuilder
{

	private WordBuilder() throws IllegalAccessException
	{
		throw new IllegalAccessException( "static-class" );
	}

	/**
	 * Convert a sequence of digits into a sequence of words.
	 * (e.g. 123 -&gt; One Two Three)
	 *
	 * @param value
	 *            the digit sequence
	 * @return as words sequence
	 */
	public static String digitsToWords( final int value )
	{
		final String valueStr = String.valueOf( value );
		final StringBuilder buffer = new StringBuilder();

		for ( final char ch : valueStr.toCharArray() )
		{
			if ( buffer.length() > 0 )
			{
				buffer.append( ' ' );
			}
			buffer.append( digitToWord( ch ) );
		}

		return buffer.toString();
	}

	/**
	 * Convert a sequence of words to a sequence of digits.
	 * (e.g. One Two Three -&gt; 123)
	 *
	 * @param value
	 *            word sequence
	 *
	 * @return as digit sequence
	 */
	public static int wordsToDigits( final String value )
	{
		final StringBuilder buffer = new StringBuilder();

		for ( final String word : value.split( "\\s" ) )
		{
			buffer.append( wordToDigit( word ) );
		}

		return Integer.valueOf( buffer.toString() );
	}

	/**
	 * Convert integer into words.
	 * (e.g. 23 -&gt; Twenty Three)
	 *
	 * @param value
	 *            the integer
	 * @return the words
	 */
	public static String intToWords( final int value )
	{
		return intToWords( intToReversedDigits( value ) );
	}

	/**
	 * Convert sequence of digits into words.s
	 *
	 * @param digits
	 *            sequence of digits
	 * @return the words
	 */
	public static String intToWords( final char... digits )
	{
		final StringBuilder buffer = new StringBuilder();
		insert( buffer, threeDigitWordBlock( 0, digits ) );
		return buffer.toString();
	}

	private static String complexHundreds( final char... digits )
	{
		if ( digits.length > 2 && digits[2] != '0' )
		{
			final StringBuilder buffer = new StringBuilder();
			buffer.append( digitToWord( digits[2] ) );
			buffer.append( multiplierToWord( 2 ) );
			return buffer.toString();
		}
		return "";
	}

	private static String threeDigitWordBlock( final int multiplier, final char... digits )
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append( complexOnesAndTens( digits ) );
		if ( digits.length > 2 && buffer.length() > 0 )
		{
			insert( buffer, " and " );
		}
		final String complexHundreds = complexHundreds( digits );
		insert( buffer, complexHundreds );
		if ( buffer.length() > 0 )
		{
			buffer.append( multiplierToWord( multiplier ) );
		}
		if ( digits.length > 3 )
		{
			insertSpace( buffer );
			// process next block of 3 digits
			insert( buffer, threeDigitWordBlock( 3 + multiplier, Arrays.copyOfRange( digits, 3, digits.length ) ) );
		}
		return buffer.toString();
	}

	private static void insert( final StringBuilder buffer, final String text )
	{
		buffer.insert( 0, text );
	}

	private static void insertSpace( final StringBuilder buffer )
	{
		if ( buffer.length() > 0 && buffer.charAt( 0 ) != ' ' )
		{
			insert( buffer, " " );
		}
	}

	private static String complexOnesAndTens( final char... digits )
	{
		// single digit
		if ( digits.length == 1 )
		{
			return digitToWord( digits[0] );
		}
		// special case - 10 to 19
		else if ( digits[1] == '1' )
		{
			return tenToNineteenToWord( digits[0] );
		}
		else
		{
			final StringBuilder buffer = new StringBuilder();
			// special case - multiples of 10
			if ( digits[1] != '0' )
			{
				buffer.append( tensToWord( digits[1] ) );
			}
			// ignore zeros
			if ( digits[0] != '0' )
			{
				appendSpace( buffer );
				buffer.append( digitToWord( digits[0] ) );
			}
			return buffer.toString();
		}
	}

	private static void appendSpace( final StringBuilder buffer )
	{
		if ( buffer.length() > 0 )
		{
			buffer.append( " " );
		}
	}

	protected static char[] intToReversedDigits( final int value )
	{
		final String string = Integer.toString( value );
		final char[] digits = new char[string.length()];
		final int length = digits.length;
		for ( int i = 0; i < length; i++ )
		{
			digits[length - 1 - i] = string.charAt( i );
		}
		return digits;
	}

	/**
	 * Convert a digit to a word.
	 *
	 * @param ch
	 *            the digit
	 * @return the word
	 */
	public static String digitToWord( final char ch )
	{
		switch ( ch )
		{
			case '0':
				return "Zero";
			case '1':
				return "One";
			case '2':
				return "Two";
			case '3':
				return "Three";
			case '4':
				return "Four";
			case '5':
				return "Five";
			case '6':
				return "Six";
			case '7':
				return "Seven";
			case '8':
				return "Eight";
			case '9':
				return "Nine";
			default:
				throw new IllegalArgumentException( "invalid-digit" );
		}
	}

	/**
	 * Convert a word to a digit.
	 *
	 * @param word
	 *            the word
	 * @return the digit
	 */
	public static char wordToDigit( final String word )
	{
		switch ( word )
		{
			case "Zero":
				return '0';
			case "One":
				return '1';
			case "Two":
				return '2';
			case "Three":
				return '3';
			case "Four":
				return '4';
			case "Five":
				return '5';
			case "Six":
				return '6';
			case "Seven":
				return '7';
			case "Eight":
				return '8';
			case "Nine":
				return '9';
			default:
				throw new IllegalArgumentException( "invalid-word" );
		}
	}

	private static String multiplierToWord( final int tens )
	{
		switch ( tens )
		{
			case 0:
			case 1:
			case 4:
			case 5:
			case 7:
			case 8:
				return "";
			case 2:
				return " Hundred";
			case 3:
				return " Thousand";
			case 6:
				return " Million";
			case 9:
				return " Billion";
			default:
				throw new IllegalArgumentException( "invalid-multiplier" );
		}
	}

	private static String tenToNineteenToWord( final char ch )
	{
		switch ( ch )
		{
			case '0':
				return "Ten";
			case '1':
				return "Eleven";
			case '2':
				return "Twelve";
			case '3':
				return "Thirteen";
			case '4':
				return "Fourteen";
			case '5':
				return "Fifteen";
			case '6':
				return "Sixteen";
			case '7':
				return "Seventeen";
			case '8':
				return "Eighteen";
			case '9':
				return "Nineteen";
			default:
				throw new IllegalArgumentException( "invalid-digit" );
		}
	}

	private static String tensToWord( final char ch )
	{
		switch ( ch )
		{
			case '1':
				return "Ten";
			case '2':
				return "Twenty";
			case '3':
				return "Thirty";
			case '4':
				return "Forty";
			case '5':
				return "Fifty";
			case '6':
				return "Sixty";
			case '7':
				return "Seventy";
			case '8':
				return "Eighty";
			case '9':
				return "Ninety";
			default:
				throw new IllegalArgumentException( "invalid-digit" );
		}
	}

}
