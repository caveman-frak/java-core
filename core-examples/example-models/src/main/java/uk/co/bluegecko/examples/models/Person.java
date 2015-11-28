package uk.co.bluegecko.examples.models;


import uk.co.bluegecko.core.model.Data;
import uk.co.bluegecko.core.model.Named;


@SuppressWarnings( "javadoc" )
public interface Person extends Data< Long >, Named
{

	public String getFirstName();

	public void setFirstName( final String firstName );

	public String getLastName();

	public void setLastName( final String firstName );

}
