/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


/**
 * Scaling factor for sizing components.
 */
public class Scale
{

	private final double factor;

	/**
	 * Create a scaling factor.
	 *
	 * @param factor
	 *            the factor to use
	 */
	public Scale( final double factor )
	{
		if ( factor < 0.0 || factor > 1.0 )
		{
			throw new IllegalArgumentException( "fill must be between 0.0 and 1.0 inclusive, was " + factor );
		}
		this.factor = factor;
	}

	/**
	 * Get the scaling factor.
	 *
	 * @return the factor
	 */
	public double getFactor()
	{
		return factor;
	}

	/**
	 * Scale based on the passed bounds.
	 *
	 * @param outer
	 *            outer bound
	 * @param inner
	 *            inner bound
	 * @return scaled value
	 */
	public int scale( final int outer, final int inner )
	{
		return Math.min( outer, ( int ) ( inner + Math.round( ( outer - inner ) * factor ) ) );
	}

	@Override
	public boolean equals( final Object obj )
	{
		if ( this == obj )
		{
			return true;
		}
		if ( obj instanceof Scale )
		{
			final Scale that = ( Scale ) obj;
			return getFactor() == that.getFactor();
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return ( int ) Math.round( factor * 1000 );
	}

	@Override
	public String toString()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append( "Scale[" );
		buffer.append( "factor=" );
		buffer.append( factor );
		buffer.append( "]" );
		return buffer.toString();
	}

	/**
	 * Constant for no scaling.
	 */
	public static final Scale NONE = new Scale( 0.0d );
	/**
	 * Constant for fully scaled.
	 */
	public static final Scale ALL = new Scale( 1.0d );

}
