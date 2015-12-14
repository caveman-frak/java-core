package uk.co.bluegecko.core.process.base;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.co.bluegecko.core.process.harness.TestHarness;
import uk.co.bluegecko.core.test.source.Source;
import uk.co.bluegecko.core.test.source.WordSequenceSource;


@SuppressWarnings( "javadoc" )
public class BulkConnectorTest extends TestHarness
{

	private Connector< String > connector;
	private Source< String > source;

	@Before
	public final void setUp()
	{
		connector = BulkConnector.bulk();
		source = new WordSequenceSource();
	}

	@Test
	public void testIsReady()
	{
		assertThat( "empty", connector.isReady(), is( false ) );
		connector.push( source.next() );
		assertThat( "pushed", connector.isReady(), is( false ) );
		connector.finished();
		assertThat( "finish", connector.isReady(), is( true ) );
	}

}