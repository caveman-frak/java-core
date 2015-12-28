package uk.co.bluegecko.core.util.collate;


import java.util.Comparator;


/**
 * Un-sortable comparator.
 *
 * @param <T>
 *            data type
 */
public final class DontSort< T > implements Comparator< T >
{

	private DontSort()
	{
		super();
	}

	@Override
	public int compare( final T o1, final T o2 )
	{
		throw new UnsupportedOperationException( "unsupported-operation" );
	}

	/**
	 * Constant no sort comparator.
	 */
	public static final DontSort< ? > DONT_SORT = new DontSort< >();

}
