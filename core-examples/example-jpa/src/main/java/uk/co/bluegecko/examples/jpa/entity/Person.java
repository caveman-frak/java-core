package uk.co.bluegecko.examples.jpa.entity;


@SuppressWarnings( "javadoc" )
public interface Person
{

	public long getId();

	public String getFirstName();

	public void setFirstName( final String firstName );

	public String getLastName();

	public void setLastName( final String firstName );

}
