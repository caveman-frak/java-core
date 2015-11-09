package uk.co.bluegecko.core.process.base;


import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import uk.co.bluegecko.core.process.Sink;


@SuppressWarnings( "javadoc" )
public class RoutingSinkTest
{

	private enum Route
	{
		FOO, BAR
	}

	private static final String ONE = "One";
	private static final String TWO = "Two";
	private static final String THREE = "Three";
	private static final String FOUR = "Four";

	private Sink< String > sink;
	private Sink< String > sinkFoo, sinkBar;

	@SuppressWarnings( "unchecked" )
	@Before
	public final void setUp()
	{
		sinkFoo = mock( Sink.class );
		sinkBar = mock( Sink.class );
		sink = new RoutingSink<>( s -> ( s.length() > 3 ? Route.BAR : Route.FOO ), Route.values(), sinkFoo, sinkBar );
	}

	@Test
	public void testFinished()
	{
		sink.finished();

		verify( sinkFoo ).finished();
		verify( sinkBar ).finished();
	}

	@Test
	public void testPushObjectToFoo()
	{
		sink.push( ONE );

		verify( sinkFoo ).push( ONE );
		verifyZeroInteractions( sinkBar );
	}

	@Test
	public void testPushObjectToBar()
	{
		sink.push( THREE );

		verifyZeroInteractions( sinkFoo );
		verify( sinkBar ).push( THREE );
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
		order.verify( sinkFoo ).push( TWO );
		order.verify( sinkBar ).push( THREE );
		order.verify( sinkBar ).push( FOUR );
		verifyNoMoreInteractions( sinkFoo, sinkBar );
	}

}
