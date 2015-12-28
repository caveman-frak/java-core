package uk.co.bluegecko.core.util.collate;


import java.util.Comparator;


/**
 * Chain comparators together.
 *
 * @param <T>
 *            data type
 */
public final class ChainSort< T > implements Comparator< T >
{

	private final Comparator< T >[] comparators;

	/**
	 * Create a new comparator.
	 *
	 * @param comparators
	 *            the sub comparators
	 */
	@SafeVarargs
	public ChainSort( final Comparator< T >... comparators )
	{
		super();

		this.comparators = comparators;
	}

	@Override
	public int compare( final T o1, final T o2 )
	{
		int result = 0;
		for ( final Comparator< T > comparator : comparators )
		{
			result = comparator.compare( o1, o2 );

			if ( result != 0 )
			{
				break;
			}
		}
		return result;
	}

	/**
	 * Create a chain comparator.
	 *
	 * @param <T>
	 *            the data type
	 * @param comparators
	 *            the sub comparators
	 * @return the comparator
	 */
	@SafeVarargs
	public static final < T > Comparator< T > chain( final Comparator< T >... comparators )
	{
		return new ChainSort< >( comparators );
	}

}
