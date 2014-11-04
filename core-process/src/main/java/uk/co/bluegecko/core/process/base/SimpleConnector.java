/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.process.base;


import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @param <T>
 *
 */
public class SimpleConnector< T > implements Connector< T >
{

	private final Queue< T > queue;
	private final AtomicBoolean finished;

	/**
	 * @param queue
	 *
	 */
	public SimpleConnector( final Queue< T > queue )
	{
		super();

		this.queue = queue;
		finished = new AtomicBoolean( false );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#push(T)
	 */
	@Override
	public void push( final T in )
	{
		queue.add( in );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#finished()
	 */
	@Override
	public void finished()
	{
		finished.set( true );
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#next()
	 */
	@Override
	public T next()
	{
		return queue.poll();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#isReady()
	 */
	@Override
	public boolean isReady()
	{
		return !queue.isEmpty();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#isFinished()
	 */
	@Override
	public boolean isFinished()
	{
		return finished.get();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#reset()
	 */
	@Override
	public void reset()
	{
		queue.clear();
		finished.set( false );
	}

}
