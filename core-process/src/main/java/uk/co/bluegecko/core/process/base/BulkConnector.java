package uk.co.bluegecko.core.process.base;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Implementation of the {@link Connector} interface for bulk operations. Is not ready until all elements have been
 * added and has been marked finished.
 *
 * @param <T>
 *            type of object to pass
 */
public class BulkConnector< T > extends SimpleConnector< T >
{

	/**
	 * @param queue
	 *            queue to use for passing objects
	 */
	protected BulkConnector( final Queue< T > queue )
	{
		super( queue );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#isReady()
	 */
	@Override
	public boolean isReady()
	{
		return isFinished() && super.isReady();
	}

	/**
	 * SimpleConnector using {@link ConcurrentLinkedQueue}.
	 *
	 * @param <T>
	 *            type of object to pass
	 * @return a new simple connector using a concurrent queue
	 */
	public static final < T > Connector< T > bulk()
	{
		return new BulkConnector< >( new ConcurrentLinkedQueue< >() );
	}

}
