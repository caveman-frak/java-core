package uk.co.bluegecko.core.util.collate;


import java.util.Comparator;


/**
 * Comparator wrapper that reverses sort order.
 *
 * @param <T>
 *            object type
 */
public final class ReverseSort< T > implements Comparator< T >
{

	private final Comparator< T > comparator;

	private ReverseSort( final Comparator< T > comparator )
	{
		super();

		this.comparator = comparator;
	}

	@Override
	public int compare( final T o1, final T o2 )
	{
		return comparator.compare( o1, o2 ) * -1;
	}

	/**
	 * Reverse the sorting of the passed comparator.
	 *
	 * @param <T>
	 *            the data type
	 * @param comparator
	 *            the base comparator
	 * @return the reverse sort comparator
	 */
	public static final < T > Comparator< T > reverse( final Comparator< T > comparator )
	{
		return new ReverseSort< >( comparator );
	}

	/**
	 * Reverse the natural sorting of comparable objects.
	 *
	 * @param <T>
	 *            the comparable data type
	 * @return the reverse sort comparator
	 */
	public static final < T extends Comparable< T > > Comparator< T > reverse()
	{
		return new ReverseSort< >( ( o1, o2 ) -> o1.compareTo( o2 ) );
	}

}
