/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import java.io.Serializable;


/**
 * A rendering hint, used to perform weighted analysis to determine cell properties.
 *
 * @param <E>
 *            type of property
 */
public abstract class RenderingHint< E > implements Serializable
{

	private static final long serialVersionUID = 7885261704211750057L;

	private final RenderingType type;
	private final HintWeight weight;
	private final E value;
	private final boolean derived;

	private RenderingHint( final RenderingType type, final HintWeight weight, final E value, final boolean derived )
	{
		super();

		this.type = type;
		this.weight = weight;
		this.value = value;
		this.derived = derived;
	}

	protected RenderingHint( final RenderingType type, final HintWeight weight, final E value )
	{
		this( type, weight, value, false );
	}

	protected RenderingHint( final RenderingType type, final HintWeight weight )
	{
		this( type, weight, null, true );
	}

	/**
	 * Get the rendering type.
	 *
	 * @return the rendering type
	 */
	public RenderingType getType()
	{
		return type;
	}

	/**
	 * Get the hint value, based on a base value. If this is a derived hint, the original value will be used to
	 * calculate the hint value.
	 *
	 * @param original
	 *            original value
	 * @return derived or hint value
	 */
	public E getValue( final E original )
	{
		return derived ? derive( original ) : value;
	}

	/**
	 * Get the hint value.
	 * 
	 * @return hint value
	 */
	public E getValue()
	{
		return value;
	}

	/**
	 * Get the hint weighting.
	 * 
	 * @return hint weight
	 */
	public HintWeight getWeight()
	{
		return weight;
	}

	protected E derive( final E original )
	{
		return original;
	}

	/**
	 * Determine if this passed hint has higher weighting.
	 * 
	 * @param that
	 *            hint to check
	 * @return true of the passed hint has higher weighting
	 */
	public boolean exceeds( final RenderingHint< ? > that )
	{
		return this.weight.getValue() > that.weight.getValue();
	}

	@Override
	public final String toString()
	{
		final StringBuilder buffer = new StringBuilder();

		buffer.append( getClass().getName() );
		buffer.append( "[type=" );
		buffer.append( type );
		buffer.append( ",weight=" );
		buffer.append( weight );
		buffer.append( ",value=" );
		buffer.append( value );
		buffer.append( "]" );

		return buffer.toString();
	}

	@Override
	public final boolean equals( final Object obj )
	{
		if ( obj == this )
		{
			return true;
		}
		if ( !( obj instanceof RenderingHint ) )
		{
			return false;
		}

		final RenderingHint< ? > that = ( RenderingHint< ? > ) obj;

		if ( that.type != this.type )
		{
			return false;
		}
		if ( that.weight != this.weight )
		{
			return false;
		}
		if ( !that.value.equals( this.value ) )
		{
			return false;
		}

		return true;
	}

	@Override
	public final int hashCode()
	{
		return super.hashCode();
	}

}
