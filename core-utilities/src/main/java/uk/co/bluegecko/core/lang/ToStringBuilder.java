package uk.co.bluegecko.core.lang;


import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * Build the toString representation of an object.
 */
public class ToStringBuilder extends org.apache.commons.lang3.builder.ToStringBuilder// ToStringCreator
{

	/**
	 * New toString builder for object.
	 * 
	 * @param obj
	 *            object to build toString
	 */
	public ToStringBuilder( final Object obj )
	{
		super( obj, ToStringStyle.SHORT_PREFIX_STYLE );
	}

	/**
	 * New toString builder for object using passed styler.
	 * 
	 * @param obj
	 *            object to build toString
	 * @param styler
	 *            styler for toString
	 */
	public ToStringBuilder( final Object obj, final ToStringStyle styler )
	{
		super( obj, styler );
	}

	/**
	 * Append field name and value if value is not null.
	 * 
	 * @param field
	 *            the field name
	 * @param value
	 *            the field value
	 * @return the ToStringBuilder
	 */
	public ToStringBuilder appendNotNull( final String field, final Object value )
	{
		if ( value != null )
			return append( field, value );
		return this;
	}

	/**
	 * Append field name and value if value is not blank.
	 * 
	 * @param field
	 *            the field name
	 * @param value
	 *            the field value
	 * @return the ToStringBuilder
	 */
	public ToStringBuilder appendNotBlank( final String field, final String value )
	{
		if ( value != null && !value.isEmpty() )
			return append( field, value );
		return this;
	}

	/**
	 * Append field value if value is not null.
	 * 
	 * @param value
	 *            the field value
	 * @return the ToStringBuilder
	 */
	public ToStringBuilder appendNotNull( final Object value )
	{
		if ( value != null )
			return append( value );
		return this;
	}

	/**
	 * Append field value if value is not blank.
	 * 
	 * @param value
	 *            the field value
	 * @return the ToStringBuilder
	 */
	public ToStringBuilder appendNotBlank( final String value )
	{
		if ( value != null && !value.isEmpty() )
			return append( value );
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, byte)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final byte value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, short)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final short value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, int)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final int value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, long)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final long value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, float)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final float value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, double)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final double value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.String, boolean)
	 */
	@Override
	public ToStringBuilder append( final String fieldName, final boolean value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	@Override
	public ToStringBuilder append( final String fieldName, final Object value )
	{
		return ( ToStringBuilder ) super.append( fieldName, value );
	}

	/**
	 * @param value
	 *            the field value
	 * @param maxLength
	 *            max length of value
	 * @return the ToStringBuilder
	 */
	public ToStringBuilder appendTruncated( final String value, final int maxLength )
	{
		return ( ToStringBuilder ) super.append( truncate( value, maxLength ) );
	}

	/**
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the field value
	 * @param maxLength
	 *            max length of value
	 * @return the ToStringBuilder
	 */
	public ToStringBuilder append( final String fieldName, final String value, final int maxLength )
	{
		return ( ToStringBuilder ) super.append( fieldName, truncate( value, maxLength ) );
	}

	private String truncate( final String text, final int maxLength )
	{
		if ( text.length() > maxLength )
			return text.substring( 0, maxLength - 3 ).concat( "..." );
		return text;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.lang3.builder.ToStringBuilder#append(java.lang.Object)
	 */
	@Override
	public ToStringBuilder append( final Object value )
	{
		return ( ToStringBuilder ) super.append( value );
	}

}
