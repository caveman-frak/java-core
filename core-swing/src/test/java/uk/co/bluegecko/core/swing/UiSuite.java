/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import uk.co.bluegecko.core.swing.components.ComponentsSuite;
import uk.co.bluegecko.core.swing.layout.LayoutSuite;
import uk.co.bluegecko.core.swing.table.TableSuite;


@RunWith( Suite.class )
@Suite.SuiteClasses(
	{ ComponentsSuite.class, LayoutSuite.class, TableSuite.class } )
public class UiSuite
{

	public static void main( final String[] args )
	{
		JUnitCore.runClasses( new Class[]
			{ UiSuite.class } );
	}

}
