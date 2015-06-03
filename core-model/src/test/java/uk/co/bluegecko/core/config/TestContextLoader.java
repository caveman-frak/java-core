package uk.co.bluegecko.core.config;


import uk.co.bluegecko.core.test.harness.AbstractTestContextLoader;


/**
 * ContextLoader using CoreConfiguration class from java-model project
 *
 */
public class TestContextLoader extends AbstractTestContextLoader
{

	protected TestContextLoader()
	{
		super( CoreConfiguration.class );
	}

}
