package uk.co.bluegecko.core.lang;


/**
 * Build an equals representation of an object.
 * <p>
 * Usage:
 *
 * <pre>
 * <code>
 * public boolean equals(Object obj)
 * {
 *     final EqualsBuilder&lt; Foo &gt; builder = new EqualsBuilder&lt;&gt;( this, obj );
 *     if ( builder.isResolved() )
 *         return builder.isSame();
 *     final Foo that = builder.getRhs();
 *     builder.append( this.getId(), that.getId() );
 *     builder.append( this.getText(), that.getText() );
 *     return builder.isEquals();
 * }</code>
 * </pre>
 *
 * @param <T>
 *            type of object to represent
 */
public class EqualsBuilder< T > extends org.apache.commons.lang3.builder.EqualsBuilder
{

	private Boolean same;
	private boolean resolved;
	private Object rhs;

	/**
	 * New equals builder.
	 */
	public EqualsBuilder()
	{
		super();

		reset();
	}

	/**
	 * @param lhs
	 *            left hand object
	 * @param rhs
	 *            right hand object
	 */
	public EqualsBuilder( final T lhs, final Object rhs )
	{
		this();

		resolve( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#appendSuper(boolean)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > appendSuper( final boolean superEquals )
	{
		return ( EqualsBuilder< T > ) super.appendSuper( superEquals );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final Object lhs, final Object rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(long, long)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final long lhs, final long rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(int, int)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final int lhs, final int rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(short, short)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final short lhs, final short rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(char, char)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final char lhs, final char rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(byte, byte)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final byte lhs, final byte rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(double, double)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final double lhs, final double rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(float, float)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final float lhs, final float rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(boolean, boolean)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final boolean lhs, final boolean rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(java.lang.Object[], java.lang.Object[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final Object[] lhs, final Object[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final long[] lhs, final long[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(int[], int[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final int[] lhs, final int[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(short[], short[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final short[] lhs, final short[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(char[], char[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final char[] lhs, final char[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(byte[], byte[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final byte[] lhs, final byte[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(double[], double[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final double[] lhs, final double[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(float[], float[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final float[] lhs, final float[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#append(boolean[], boolean[])
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public EqualsBuilder< T > append( final boolean[] lhs, final boolean[] rhs )
	{
		return ( EqualsBuilder< T > ) super.append( lhs, rhs );
	}

	/**
	 * @param lhs
	 *            left hand object
	 * @param rhs
	 *            right hand object
	 * @return the EqualsBuilder
	 */
	public EqualsBuilder< T > resolve( final T lhs, final Object rhs )
	{
		if ( !resolved )
		{
			this.rhs = rhs;
			same = null;
			if ( lhs == rhs )
			{
				same = Boolean.TRUE;
			}
			else if ( rhs == null )
			{
				same = Boolean.FALSE;
			}
			else if ( lhs.getClass() != rhs.getClass() )
			{
				same = Boolean.FALSE;
			}
			resolved = true;
		}
		if ( same != null )
		{
			setEquals( same );
		}
		return this;
	}

	/**
	 * @return if the equals has been resolved using the early resolution check
	 */
	public boolean isResolved()
	{
		return resolved && same != null;
	}

	/**
	 * @return the value of the early resolution check
	 */
	public boolean isSame()
	{
		return isResolved() && same.booleanValue();
	}

	/**
	 * @return the right hand argument, cast as left hand if appropriate.
	 */
	@SuppressWarnings(
		{ "unchecked", "null" } )
	public T getRhs()
	{
		return resolved && isEquals() ? ( T ) rhs : null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#isEquals()
	 */
	@Override
	public boolean isEquals()
	{
		return isResolved() ? isSame() : super.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.EqualsBuilder#reset()
	 */
	@Override
	public void reset()
	{
		same = null;
		resolved = false;

		super.reset();
	}

}
