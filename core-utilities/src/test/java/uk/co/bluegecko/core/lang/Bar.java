/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.lang;


@SuppressWarnings( "javadoc" )
public class Bar implements Comparable< Bar >
{

	private final Long id;
	private final String text;

	public Bar( final long id, final String text )
	{
		this.id = id;
		this.text = text;
	}

	@Override
	public int compareTo( final Bar other )
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
