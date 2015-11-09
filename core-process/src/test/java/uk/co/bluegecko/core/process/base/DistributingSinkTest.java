package uk.co.bluegecko.core.process.base;


import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import uk.co.bluegecko.core.process.Sink;


@SuppressWarnings( "javadoc" )
public class DistributingSinkTest
{

	private static final String ONE = "One";
	private static final String TWO = "Two";
	private static final String THREE = "Three";
	private static final String FOUR = "Four";

	private Sink< String > sink;
	private Sink< String > sinkFoo, sinkBar;

	@Before
	public final void setUp()
	{
		sinkFoo = mock( Sink.class );
		sinkBar = mock( Sink.class );
		sink = new DistributingSink<>( sinkFoo, sinkBar );
	}

	@Test
	public void testFinished()
	{
		sink.finished();

		verify( sinkFoo ).finished();
		verify( sinkBar ).finished();
	}

	@Test
	public void testPushObject()
	{
		sink.push( ONE );

		verify( sinkFoo ).push( ONE );
		verify( sinkBar ).push( ONE );
	}

	@Test
	public void testPushMultipleObjects()
	{
		sink.push( ONE );
		sink.push( TWO );
		sink.push( THREE );
		sink.push( FOUR );

		final InOrder order = inOrder( sinkFoo, sinkBar );
		order.verify( sinkFoo ).push( ONE );
		order.verify( sinkBar ).push( ONE );
		order.verify( sinkFoo ).push( TWO );
		order.verify( sinkBar ).push( TWO );
		order.verify( sinkFoo ).push( THREE );
		order.verify( sinkBar ).push( THREE );
		order.verify( sinkFoo ).push( FOUR );
		order.verify( sinkBar ).push( FOUR );
		verifyNoMoreInteractions( sinkFoo, sinkBar );
	}

}
