/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.antlr4.game;


import java.util.Map;
import java.util.Scanner;


@SuppressWarnings( "javadoc" )
public class Game
{

	private final String name;
	private final Map< String, Integer > points;
	private final String[][] grid;
	private int score = 0;

	public Game( final String name, final Map< String, Integer > points, final String[][] grid )
	{
		this.name = name;
		this.points = points;
		this.grid = grid;
	}

	public void play()
	{
		try (final Scanner in = new Scanner( System.in ))
		{

			System.out.println( "You're playing " + name + "." );

			while ( true )
			{
				System.out.println( "Where do you want to dig (enter x then y)?" );

				final int x = in.nextInt();
				final int y = in.nextInt();

				if ( grid[x][y] != null )
				{
					final String treasure = grid[x][y];
					score += points.get( treasure );
					grid[x][y] = null;
					System.out.println( "You found " + treasure + "! Your score is " + score + "." );
				}
				else
				{
					System.out.println( "Sorry, nothing there!" );
				}
			}
		}
	}
}