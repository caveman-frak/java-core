package uk.co.bluegecko.core.test.data;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class WordBuilderTest
{

	@Test
	public final void testIntToWords()
	{
		assertThat( "0", WordBuilder.intToWords( 0 ), is( "Zero" ) );
		assertThat( "10", WordBuilder.intToWords( 10 ), is( "One Zero" ) );
		assertThat( "26", WordBuilder.intToWords( 26 ), is( "Two Six" ) );
		assertThat( "987", WordBuilder.intToWords( 987 ), is( "Nine Eight Seven" ) );
	}

	@Test
	public final void testWordsToInt()
	{
		assertThat( "0", WordBuilder.wordsToInt( "Zero" ), is( 0 ) );
		assertThat( "10", WordBuilder.wordsToInt( "One Zero" ), is( 10 ) );
		assertThat( "26", WordBuilder.wordsToInt( "Two Six" ), is( 26 ) );
		assertThat( "987", WordBuilder.wordsToInt( "Nine Eight Seven" ), is( 987 ) );
	}

	@Test
	public final void testIntToReversedDigits()
	{
		assertThat( "0", WordBuilder.intToReversedDigits( 0 ), is( new char[]
				{ '0' } ) );
		assertThat( "10", WordBuilder.intToReversedDigits( 10 ), is( new char[]
				{ '0', '1' } ) );
		assertThat( "26", WordBuilder.intToReversedDigits( 26 ), is( new char[]
				{ '6', '2' } ) );
		assertThat( "987", WordBuilder.intToReversedDigits( 987 ), is( new char[]
				{ '7', '8', '9' } ) );
	}

	@Test
	public final void testIntToComplexWords()
	{
		assertThat( "0", WordBuilder.intToComplexWords( 0 ), is( "Zero" ) );
		assertThat( "10", WordBuilder.intToComplexWords( 10 ), is( "Ten" ) );
		assertThat( "12", WordBuilder.intToComplexWords( 12 ), is( "Twelve" ) );
		assertThat( "26", WordBuilder.intToComplexWords( 26 ), is( "Twenty Six" ) );
		assertThat( "912", WordBuilder.intToComplexWords( 912 ), is( "Nine Hundred and Twelve" ) );
		assertThat( "987", WordBuilder.intToComplexWords( 987 ), is( "Nine Hundred and Eighty Seven" ) );
		assertThat( "4000", WordBuilder.intToComplexWords( 4000 ), is( "Four Thousand" ) );
		assertThat( "4001", WordBuilder.intToComplexWords( 4001 ), is( "Four Thousand and One" ) );
		assertThat( "4100", WordBuilder.intToComplexWords( 4100 ), is( "Four Thousand One Hundred" ) );
		assertThat( "14101", WordBuilder.intToComplexWords( 14101 ), is( "Fourteen Thousand One Hundred and One" ) );
		assertThat( "40101", WordBuilder.intToComplexWords( 40101 ), is( "Forty Thousand One Hundred and One" ) );
		assertThat( "101000", WordBuilder.intToComplexWords( 101000 ), is( "One Hundred and One Thousand" ) );
		assertThat( "40000001", WordBuilder.intToComplexWords( 40000001 ), is( "Forty Million and One" ) );
		assertThat( "40101000", WordBuilder.intToComplexWords( 40101000 ),
				is( "Forty Million One Hundred and One Thousand" ) );
		assertThat(
				"MAX",
				WordBuilder.intToComplexWords( Integer.MAX_VALUE ),
				is( "Two Billion One Hundred and Forty Seven Million Four Hundred and Eighty Three Thousand Six Hundred and Forty Seven" ) );
	}

}
