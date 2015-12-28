/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.components;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@SuppressWarnings( "javadoc" )
@RunWith( Suite.class )
@Suite.SuiteClasses(
	{ SizedHelperTest.class } )
public class ComponentsSuite
{

	public static void main( final String[] args )
	{
		JUnitCore.runClasses( new Class[]
			{ ComponentsSuite.class } );
	}

}
