/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.process.base;


import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import uk.co.bluegecko.core.process.base.ConnectorException.Message;
import uk.co.bluegecko.core.service.common.LocaleService;


/**
 * Simple implementation of the {@link Connector} interface.
 *
 * @param <T>
 *            type of object to pass
 */
public class SimpleConnector< T > implements Connector< T >
{

	private final LocaleService localeService;
	private final Queue< T > queue;
	private final AtomicBoolean finished;

	/**
	 * @param queue
	 *            queue to use for passing objects
	 * @param localeService
	 *            used to determine locale for exceptions and logs
	 */
	protected SimpleConnector( final Queue< T > queue, final LocaleService localeService )
	{
		super();

		this.queue = queue;
		this.localeService = localeService;
		finished = new AtomicBoolean( false );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#push(T)
	 */
	@Override
	public void push( final T in )
	{
		if ( !finished.get() )
		{
			queue.add( in );
		}
		else
		{
			throw new ConnectorException( localeService.getSystemLocale(), Message.PUSH_ON_FINISH );
		}
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#finished()
	 */
	@Override
	public void finished()
	{
		finished.set( true );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#next()
	 */
	@Override
	public T next()
	{
		return queue.poll();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#isReady()
	 */
	@Override
	public boolean isReady()
	{
		return !queue.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#isFinished()
	 */
	@Override
	public boolean isFinished()
	{
		return finished.get();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.base.Connector#reset()
	 */
	@Override
	public void reset()
	{
		queue.clear();
		finished.set( false );
	}

	/**
	 * SimpleConnector using {@link ConcurrentLinkedQueue}.
	 *
	 * @param <T>
	 *            type of object to pass
	 * @param localeService
	 *            locale to use for logging and exception reporting
	 *
	 * @return a new simple connector using a concurrent queue
	 */
	public static final < T > Connector< T > concurrent( final LocaleService localeService )
	{
		return new SimpleConnector<>( new ConcurrentLinkedQueue<>(), localeService );
	}

	/**
	 * SimpleConnector using {@link ArrayBlockingQueue}.
	 *
	 * @param <T>
	 *            type of object to pass
	 * @param capacity
	 *            bounded capacity of the underlying queue
	 * @param localeService
	 *            locale to use for logging and exception reporting
	 *
	 * @return a new simple connector using a bounded blocking queue
	 */
	public static final < T > Connector< T > blocking( final int capacity, final LocaleService localeService )
	{
		return new SimpleConnector<>( new ArrayBlockingQueue<>( capacity ), localeService );
	}

	/**
	 * SimpleConnector using {@link LinkedBlockingQueue}.
	 *
	 * @param <T>
	 *            type of object to pass
	 * @param localeService
	 *            locale to use for logging and exception reporting
	 *
	 * @return a new simple connector using an unbounded blocking queue
	 */
	public static final < T > Connector< T > blocking( final LocaleService localeService )
	{
		return new SimpleConnector<>( new LinkedBlockingQueue<>(), localeService );
	}

}
