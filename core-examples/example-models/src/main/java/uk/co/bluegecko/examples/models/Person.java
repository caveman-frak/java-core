package uk.co.bluegecko.examples.models;


import uk.co.bluegecko.core.model.Data;
import uk.co.bluegecko.core.model.Named;


@SuppressWarnings( "javadoc" )
public interface Person extends Data< Long >, Named
{

	public Title getTitle();

	public String getFirstName();

	public String getLastName();

}
