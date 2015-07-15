package uk.co.bluegecko.core.server.model.base;


import static uk.co.bluegecko.core.helper.ByteValues.BYTE_SUFFIX;
import static uk.co.bluegecko.core.helper.ByteValues.scale;

import java.lang.management.MemoryUsage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uk.co.bluegecko.core.server.model.Memory;


@SuppressWarnings( "javadoc" )
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public final class BaseMemory implements Memory
{

	private final String initial;
	private final String used;
	private final String committed;
	private final String maximum;

	public BaseMemory( final MemoryUsage usage )
	{
		initial = asText( usage.getInit() );
		used = asText( usage.getUsed() );
		committed = asText( usage.getCommitted() );
		maximum = asText( usage.getMax() );
	}

	public BaseMemory()
	{
		initial = "";
		used = "";
		committed = "";
		maximum = "";
	}

	private String asText( final long amount )
	{
		return scale( amount, BYTE_SUFFIX );
	}

	@Override
	public String getInitial()
	{
		return initial;
	}

	@Override
	public String getUsed()
	{
		return used;
	}

	@Override
	public String getCommitted()
	{
		return committed;
	}

	@Override
	public String getMaximum()
	{
		return maximum;
	}

}