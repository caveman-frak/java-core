package uk.co.bluegecko.core.process.base;


import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.bluegecko.core.test.exception.ThrowableCaptor.capture;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.process.harness.TestHarness;
import uk.co.bluegecko.core.test.source.Source;
import uk.co.bluegecko.core.test.source.WordSequenceSource;


@SuppressWarnings( "javadoc" )
public class SimpleConnectorTest extends TestHarness
{

	private Connector< String > connector;
	private Source< String > source;

	@Before
	public final void setUp()
	{
		connector = SimpleConnector.concurrent( localeService() );
		source = new WordSequenceSource();
	}

	@Test
	public void testIsReady()
	{
		assertThat( "empty", connector.isReady(), is( false ) );
		connector.push( source.next() );
		assertThat( "pushed", connector.isReady(), is( true ) );
	}

	@Test
	public void testIsFinished()
	{
		assertThat( "empty", connector.isFinished(), is( false ) );
		connector.push( source.next() );
		assertThat( "pushed", connector.isFinished(), is( false ) );
		connector.finished();
		assertThat( "finish", connector.isFinished(), is( true ) );
	}

	@Test
	public void testPush()
	{
		connector.push( source.next() );
		assertThat( "before finish", connector.isReady(), is( true ) );
		connector.finished();
		final Throwable caught = capture( "push", ( ) -> connector.push( source.next() ) );

		assertThat( "after finish - type", caught, is( instanceOf( ConnectorException.class ) ) );
		assertThat( "after finish - text", caught.getLocalizedMessage(),
				is( "Can't push object to a closed connectors" ) );
	}

	@Test
	public void testNextFiFo()
	{

		connector.push( source.next() );
		connector.push( source.next() );
		assertThat( connector.next(), is( "One" ) );
		assertThat( connector.next(), is( "Two" ) );
	}

}
