/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith( Suite.class )
@Suite.SuiteClasses(
	{ AlignmentTest.class, ConstraintTest.class, ScaleTest.class, LinkTest.class, SizeTest.class, SpacerTest.class,
			FormLayoutTest.class, FoamTest.class } )
public class LayoutSuite
{

	public static void main( final String[] args )
	{
		JUnitCore.runClasses( new Class[]
			{ LayoutSuite.class } );
	}

}
