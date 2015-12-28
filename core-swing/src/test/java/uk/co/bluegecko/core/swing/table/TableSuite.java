/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.table;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import uk.co.bluegecko.core.swing.table.rendering.RenderingHelperTest;
import uk.co.bluegecko.core.swing.table.rendering.RenderingHintTest;


@SuppressWarnings( "javadoc" )
@RunWith( Suite.class )
@Suite.SuiteClasses(
	{ DefaultColumnAttributesTest.class, RenderingHelperTest.class, RenderingHintTest.class } )
public class TableSuite
{

	public static void main( final String[] args )
	{
		JUnitCore.runClasses( new Class[]
			{ TableSuite.class } );
	}

}
