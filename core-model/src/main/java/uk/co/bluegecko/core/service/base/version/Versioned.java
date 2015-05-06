package uk.co.bluegecko.core.service.base.version;


import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * A collection of versioned instances of a type.
 *
 * @param <T>
 *            the type to collect
 */
public class Versioned< T >
{

	private final SortedMap< Version, T > map;

	/**
	 * Construct a new
	 *
	 * @param collection
	 *            collection of versioned type T
	 */
	public Versioned( final Collection< T > collection )
	{
		super();

		this.map = new TreeMap<>();

		populate( collection );
	}

	protected Map< Version, T > getMap()
	{
		return Collections.unmodifiableSortedMap( map );
	}

	/**
	 * Return sorted set of available versions.
	 *
	 * @return available versions
	 */
	public SortedSet< Version > getVersions()
	{
		return Collections.unmodifiableSortedSet( new TreeSet<>( map.keySet() ) );
	}

	private void populate( final Collection< T > collection )
	{
		for ( final T object : collection )
		{
			final VersionInfo versionInfo = object.getClass().getAnnotation( VersionInfo.class );
			if ( versionInfo == null ) { throw new IllegalArgumentException( "class must be versioned" ); }

			map.put( new FullVersion( versionInfo ), object );
		}
	}

	/**
	 * Return the latest version of the contained type.
	 *
	 * @return the contained type
	 */
	public T get()
	{
		return get( PartialVersion.LATEST );
	}

	/**
	 * Return the latest version of the contained type, after the passed version.
	 *
	 * @param version
	 *            the minimum version
	 * @return the contained type
	 */
	public T get( final Version version )
	{
		return get( FullVersion.FIRST, version );
	}

	/**
	 * Return the latest version of the contained type, between the bounded versions.
	 *
	 * @param lower
	 *            the lower version
	 * @param upper
	 *            the upper version
	 * @return the contained type
	 */
	public T get( final Version lower, final Version upper )
	{
		if ( map.containsKey( upper ) ) { return map.get( upper ); }
		final SortedMap< Version, T > head = map.subMap( lower, upper );
		if ( head.isEmpty() ) { return null; }
		return head.get( head.lastKey() );
	}

}
