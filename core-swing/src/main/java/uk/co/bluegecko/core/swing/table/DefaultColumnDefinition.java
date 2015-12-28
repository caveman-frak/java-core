package uk.co.bluegecko.core.swing.table;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;


/**
 * Concrete implementation of a {@link ColumnDefinition}.
 *
 * @param <T>
 *            the data type
 */
public class DefaultColumnDefinition< T > implements ColumnDefinition< T >
{

	private final String identifier;
	private final Class< T > klass;
	private final Comparator< T > comparator;
	private final boolean editable;

	/**
	 * Create a column definition.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param klass
	 *            the type
	 * @param comparator
	 *            the comparator
	 * @param editable
	 *            is editable
	 */
	public DefaultColumnDefinition( final String identifier, final Class< T > klass, final Comparator< T > comparator,
			final boolean editable )
	{
		super();

		this.identifier = identifier;
		this.klass = klass;
		this.comparator = comparator;
		this.editable = editable;
	}

	/**
	 * Create a column definition.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param klass
	 *            the type
	 * @param editable
	 *            is editable
	 */
	public DefaultColumnDefinition( final String identifier, final Class< T > klass, final boolean editable )
	{
		this( identifier, klass, null, editable );
	}

	@Override
	public String getIdentifier()
	{
		return identifier;
	}

	@Override
	public Class< T > getColumnClass()
	{
		return klass;
	}

	@Override
	public boolean isEditable()
	{
		return editable;
	}

	@Override
	public Comparator< T > getComparator()
	{
		return comparator;
	}

	/**
	 * Create a column definition for object columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Object > objectColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Object.class, editable );
	}

	/**
	 * Create a column definition for string columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< String > stringColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, String.class, editable );
	}

	/**
	 * Create a column definition for integer columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Integer > integerColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Integer.class, editable );
	}

	/**
	 * Create a column definition for long columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Long > longColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Long.class, editable );
	}

	/**
	 * Create a column definition for float columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Float > floatColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Float.class, editable );
	}

	/**
	 * Create a column definition for double columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Double > doubleColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Double.class, editable );
	}

	/**
	 * Create a column definition for big integer columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< BigInteger > bigIntegerColumn( final String identifier,
			final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, BigInteger.class, editable );
	}

	/**
	 * Create a column definition for big decimal columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< BigDecimal > bigDecimalColumn( final String identifier,
			final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, BigDecimal.class, editable );
	}

	/**
	 * Create a column definition for date columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Date > dateColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Date.class, editable );
	}

	/**
	 * Create a column definition for calendar columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Calendar > calendarColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Calendar.class, editable );
	}

	/**
	 * Create a column definition for boolean columns.
	 * 
	 * @param identifier
	 *            the identifier
	 * @param editable
	 *            is editable
	 * @return the column definition
	 */
	public static DefaultColumnDefinition< Boolean > booleanColumn( final String identifier, final boolean editable )
	{
		return new DefaultColumnDefinition< >( identifier, Boolean.class, editable );
	}

}
