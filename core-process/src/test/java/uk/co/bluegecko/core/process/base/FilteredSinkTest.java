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
public class FilteredSinkTest
{

	private static final String ONE = "One";
	private static final String ONE_LOWER = "one";
	private static final String ONE_UPPER = "ONE";
	private static final String TWO = "Two";

	private Sink< String > sinkFoo;
	private Sink< String > sink;

	@Before
	public final void setUp()
	{
		sinkFoo = mock( Sink.class );
		sink = new FilteredSink< >( sinkFoo, s -> s.equalsIgnoreCase( ONE ) );
	}

	@Test
	public void testFinished()
	{
		sink.finished();

		verify( sinkFoo ).finished();
	}

	@Test
	public void testPushValidObject()
	{
		sink.push( ONE );

		verify( sinkFoo ).push( ONE );
	}

	@Test
	public void testPushInvalidObject()
	{
		sink.push( TWO );

		verifyZeroInteractions( sinkFoo );
	}

	@Test
	public void testPushMultipleObjects()
	{
		sink.push( ONE );
		sink.push( ONE_LOWER );
		sink.push( ONE_UPPER );
		sink.push( TWO );

		final InOrder order = inOrder( sinkFoo );
		order.verify( sinkFoo ).push( ONE );
		order.verify( sinkFoo ).push( ONE_LOWER );
		order.verify( sinkFoo ).push( ONE_UPPER );
		verifyNoMoreInteractions( sinkFoo );
	}

}
