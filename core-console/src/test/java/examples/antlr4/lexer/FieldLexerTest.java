/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package examples.antlr4.lexer;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.junit.Test;

import examples.antlr.field.lexer.FieldLexer;
import examples.antlr.field.lexer.FieldParser;


@SuppressWarnings( "javadoc" )
public class FieldLexerTest
{

	@Test
	public void testExampleField() throws Exception
	{
		final FieldLexer lexer = new FieldLexer( new ANTLRInputStream( getClass().getResourceAsStream(
				"/antlr4/game/example.field" ) ) );
		final FieldParser parser = new FieldParser( new CommonTokenStream( lexer ) );
		parser.addErrorListener( new BaseErrorListener()
		{

			@Override
			public void syntaxError( final Recognizer< ?, ? > recognizer, final Object offendingSymbol, final int line,
					final int charPositionInLine, final String msg, final RecognitionException e )
			{
				throw new IllegalStateException( "failed to parse at line " + line + " due to " + msg, e );
			}
		} );
		parser.field();
	}

}
