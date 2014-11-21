/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.antlr4.game;


@SuppressWarnings( "javadoc" )
public class Application
{

	public static void main( final String[] args ) throws Exception
	{
		final Game game = new GameFactory().createGame( Application.class.getResourceAsStream( "/example.field" ) );

		game.play();
	}
}
