/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.test.matcher;


import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


/**
 * Check if the passed object has a field with of the correct value
 *
 * @param <M>
 *            the type of object to check
 * @param <V>
 *            the type of value to check
 */
public abstract class AbstractValueMatcher< M, V > extends TypeSafeMatcher< M >
{

	/**
	 * The value to check against the object's field
	 */
	private final V value;

	/**
	 * Construct a new matcher, checking for the supplied field value
	 *
	 * @param value
	 *            the value to check against the object's field
	 */
	protected AbstractValueMatcher( final V value )
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo( final Description description )
	{
		description.appendText( getDescription() ).appendText( " " ).appendValue( value );
	}

	/**
	 * Return a description for the match being performed
	 *
	 * @return the description in the form
	 *         <p>
	 *         <code> return "an &lt;Object&gt; with &lt;Field&gt;";
	 * </code>
	 */
	protected abstract String getDescription();

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	protected boolean matchesSafely( final M obj )
	{
		return getValue( obj ).equals( value );
	}

	/**
	 * Get the value for the field of the passed object
	 *
	 * @param obj
	 *            the object to query for the field
	 * @return the value of the field
	 */
	protected abstract V getValue( M obj );

	/*
	 * (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#describeMismatchSafely(java.lang.Object, org.hamcrest.Description)
	 */
	@Override
	protected void describeMismatchSafely( final M model, final Description mismatchDescription )
	{
		mismatchDescription.appendText( "was " ).appendText( getDescription() ).appendText( " " )
				.appendValue( getValue( model ) ).appendText( " expected " ).appendValue( value );
	}

}