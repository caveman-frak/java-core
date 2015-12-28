/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.util.resources;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@SuppressWarnings( "javadoc" )
@RunWith( Suite.class )
@Suite.SuiteClasses(
	{ LocalisationHelperTest.class, ResourceHandlerTest.class } )
public class ResourceSuite
{

	public static void main( final String[] args )
	{
		JUnitCore.runClasses( new Class[]
			{ ResourceSuite.class } );
	}

}
