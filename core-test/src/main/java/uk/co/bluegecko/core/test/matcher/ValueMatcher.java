/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher;


import java.util.function.Function;

import org.hamcrest.Matcher;


/**
 * Check if the passed object has a field with of the correct value
 *
 * @param <M>
 *            the type of object to check
 * @param <V>
 *            the type of value to check
 */
public class ValueMatcher< M, V > extends AbstractValueMatcher< M, V >
{

	private final String objectName;
	private final String fieldName;
	private final Function< M, V > function;

	/**
	 * Create a new ValueMatcher with the following parameters.
	 *
	 * @param objectName
	 *            the name of the object
	 * @param fieldName
	 *            the name of the field
	 * @param value
	 *            the expected value of the field
	 * @param function
	 *            the function to extract the actual field value
	 */
	public ValueMatcher( final String objectName, final String fieldName, final V value, final Function< M, V > function )
	{
		super( value );

		this.objectName = objectName;
		this.fieldName = fieldName;
		this.function = function;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.matcher.AbstractValueMatcher#getDescription()
	 */
	@Override
	protected String getDescription()
	{
		return "a " + objectName + " with " + fieldName;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.test.matcher.AbstractValueMatcher#getValue(java.lang.Object)
	 */
	@Override
	protected V getValue( final M obj )
	{
		return function.apply( obj );
	}

	/**
	 * Create a new ValueMatcher with the following parameters.
	 *
	 * @param <M>
	 *            the type of object to check
	 * @param <V>
	 *            the type of value to check
	 * @param objectName
	 *            the name of the object
	 * @param fieldName
	 *            the name of the field
	 * @param value
	 *            the expected value of the field
	 * @param function
	 *            the function to extract the actual field value
	 * @return the new Matcher
	 */
	public static < M, V > Matcher< M > value( final String objectName, final String fieldName, final V value,
			final Function< M, V > function )
	{
		return new ValueMatcher<>( objectName, fieldName, value, function );
	}

}
