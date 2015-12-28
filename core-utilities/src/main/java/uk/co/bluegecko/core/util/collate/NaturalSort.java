package uk.co.bluegecko.core.util.collate;


import java.util.Comparator;


/**
 * Comparator for comparable objects.
 *
 * @param <T>
 *            object type, must extend comparable
 */
public final class NaturalSort< T extends Comparable< T > > implements Comparator< T >
{

	private NaturalSort()
	{
		super();
	}

	@Override
	public int compare( final T o1, final T o2 )
	{
		return o1.compareTo( o2 );
	}

	/**
	 * Create a new natural comparator.
	 *
	 * @param <T>
	 *            the comparable data type
	 * @return the comparator
	 */
	public static final < T extends Comparable< T > > NaturalSort< T > naturalSort()
	{
		return new NaturalSort< >();
	}

}
