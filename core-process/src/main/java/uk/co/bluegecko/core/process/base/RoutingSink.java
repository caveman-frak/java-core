package uk.co.bluegecko.core.process.base;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import uk.co.bluegecko.core.process.Sink;


/**
 * Sink that internally redirects each input to a particular wrapped sink.
 *
 * @param <T>
 *            type of input
 * @param <E>
 *            enum used for routing
 */
public class RoutingSink< T, E extends Enum< ? >> implements Sink< T >
{

	private final Map< E, Sink< T >> map;
	private final Function< T, E > router;

	/**
	 * Create a mapping of routes to sinks.
	 *
	 * @param router
	 *            function to determine which sink to route an input to
	 * @param map
	 *            maps a route to a sink
	 */
	public RoutingSink( final Function< T, E > router, final Map< E, Sink< T >> map )
	{
		this.router = router;
		this.map = map;
	}

	/**
	 * Create a mapping of routes to sinks.
	 * NOTE the number of routes must match the number of sinks.
	 *
	 * @param router
	 *            function to determine which sink to route an input to
	 * @param routes
	 *            array of possible routes (generally in the form Enum.values() )
	 * @param sinks
	 *            array of possible sinks
	 */
	@SafeVarargs
	public RoutingSink( final Function< T, E > router, final E[] routes, final Sink< T >... sinks )
	{
		this( router, mappedSinks( routes, sinks ) );
	}

	private static < T, E extends Enum< ? >> Map< E, Sink< T >> mappedSinks( final E[] routes, final Sink< T >[] sinks )
	{
		assert sinks.length == routes.length;

		final Map< E, Sink< T >> map = new HashMap<>();

		for ( int i = 0; i < routes.length; i++ )
		{
			map.put( routes[i], sinks[i] );
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#push(java.lang.Object)
	 */
	@Override
	public void push( final T in )
	{
		final E route = router.apply( in );
		if ( route != null )
		{
			final Sink< T > sink = map.get( route );
			if ( sink != null )
			{
				sink.push( in );
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.process.Sink#finished()
	 */
	@Override
	public void finished()
	{
		for ( final Sink< T > sink : map.values() )
		{
			sink.finished();
		}
	}

}
