package uk.co.bluegecko.core.server.model;


@SuppressWarnings( "javadoc" )
public interface Memory
{

	public String getMaximum();

	public String getCommitted();

	public String getUsed();

	public String getInitial();

}
