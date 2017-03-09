/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.lang;


public class Foo implements Comparable< Foo >
{

	private final Long id;
	private final String text;

	public Foo( final long id, final String text )
	{
		this.id = id;
		this.text = text;
	}

	@Override
	public int compareTo( final Foo other )
	{
		return id.compareTo( other.id );
	}

	public Long getId()
	{
		return id;
	}

	public String getText()
	{
		return text;
	}

}
