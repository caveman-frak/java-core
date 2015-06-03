package uk.co.bluegecko.core.server.model;


import static uk.co.bluegecko.core.helper.SignificantNumbers.SUFFIX;
import static uk.co.bluegecko.core.helper.SignificantNumbers.scale;

import java.lang.management.MemoryUsage;


@SuppressWarnings( "javadoc" )
public final class Memory
{

	private final String initial;
	private final String used;
	private final String commited;
	private final String maximum;

	public Memory( final MemoryUsage usage )
	{
		initial = asText( usage.getInit() );
		used = asText( usage.getUsed() );
		commited = asText( usage.getCommitted() );
		maximum = asText( usage.getMax() );
	}

	public Memory()
	{
		initial = "";
		used = "";
		commited = "";
		maximum = "";
	}

	private String asText( final long amount )
	{
		return scale( amount, SUFFIX );
	}

	public String getInitial()
	{
		return initial;
	}

	public String getUsed()
	{
		return used;
	}

	public String getCommited()
	{
		return commited;
	}

	public String getMaximum()
	{
		return maximum;
	}

}