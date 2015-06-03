/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.harness;


import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import ch.qos.cal10n.verifier.Cal10nError;
import ch.qos.cal10n.verifier.IMessageKeyVerifier;
import ch.qos.cal10n.verifier.MessageKeyVerifier;


/**
 * Base Test class
 */
@Configuration
@RunWith( SpringJUnit4ClassRunner.class )
@TestExecutionListeners( listeners =
	{ DependencyInjectionTestExecutionListener.class } )
@ContextConfiguration( "classpath:test-context.xml" )
public class TestHarness implements ApplicationContextAware
{

	private ApplicationContext applicationContext;

	/**
	 * Default constructor
	 */
	public TestHarness()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext( final ApplicationContext applicationContext ) throws BeansException
	{
		this.applicationContext = applicationContext;
	}

	protected ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	protected void verifyLocalisation( final Class< ? extends Enum< ? >> klass )
	{
		final IMessageKeyVerifier verifier = new MessageKeyVerifier( klass );
		final List< Cal10nError > errorList = verifier.verifyAllLocales();
		for ( final Cal10nError error : errorList )
		{
			System.err.println( error );
		}
		assertThat( errorList, is( empty() ) );
	}

}
