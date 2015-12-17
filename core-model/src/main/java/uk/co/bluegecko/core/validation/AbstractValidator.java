package uk.co.bluegecko.core.validation;


import java.time.temporal.TemporalAccessor;

import org.apache.commons.lang3.StringUtils;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import uk.co.bluegecko.core.model.Message;
import uk.co.bluegecko.core.model.Messages;
import uk.co.bluegecko.core.model.Messages.Severity;
import uk.co.bluegecko.core.model.base.BundleMessage;
import uk.co.bluegecko.core.model.base.EnumMessage;


/**
 * Base class for validators. Contains common validation methods.
 *
 */
public abstract class AbstractValidator
{

	/**
	 * Standard validations.
	 */
	@SuppressWarnings( "javadoc" )
	@BaseName( "uk.co.bluegecko.core.validation.AbstractValidator$Key" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) })
	public enum Key
	{
		NOT_NULL, LENGTH_MAX, LENGTH_MIN, LENGTH_BETWEEN, REGEX, NUMBER_MAX, NUMBER_MIN, NUMBER_BETWEEN, DATE_MAX, DATE_MIN, DATE_BETWEEN;
	}

	/**
	 * Validate a field not being null.
	 *
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 */
	public void notNull( final Messages messages, final Severity severity, final String field, final Object value )
	{
		if ( value == null )
		{
			messages.addMessages( severity, field, getMessage( Key.NOT_NULL, field ) );
		}
	}

	/**
	 * Validate a string field is within maximum length.
	 *
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param max
	 *            maximum length
	 */
	public void lengthMax( final Messages messages, final Severity severity, final String field, final String value,
			final int max )
	{
		if ( StringUtils.isNotEmpty( value ) && value.length() > max )
		{
			messages.addMessages( severity, field, getMessage( Key.LENGTH_MAX, field, value, max ) );
		}
	}

	/**
	 * Validate a string field exceeds minimum length.
	 *
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param min
	 *            minimum length
	 */
	public void lengthMin( final Messages messages, final Severity severity, final String field, final String value,
			final int min )
	{
		if ( StringUtils.isNotEmpty( value ) && value.length() < min )
		{
			messages.addMessages( severity, field, getMessage( Key.LENGTH_MIN, field, value, min ) );
		}
	}

	/**
	 * Validate a string field is between minimum and maximum length.
	 *
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param min
	 *            minimum length
	 * @param max
	 *            maximum length
	 */
	public void lengthBetween( final Messages messages, final Severity severity, final String field, final String value,
			final int min, final int max )
	{
		if ( StringUtils.isNotEmpty( value ) && ( value.length() < min || value.length() > max ) )
		{
			messages.addMessages( severity, field, getMessage( Key.LENGTH_BETWEEN, field, value, min, max ) );
		}
	}

	/**
	 * Validate a string field matches regex pattern.
	 *
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param regex
	 *            regex pattern
	 */
	public void regex( final Messages messages, final Severity severity, final String field, final String value,
			final String regex )
	{
		if ( StringUtils.isNotEmpty( value ) && !value.matches( regex ) )
		{
			messages.addMessages( severity, field, getMessage( Key.REGEX, field, value, regex ) );
		}
	}

	/**
	 * Validate a numeric field is within maximum value.
	 *
	 * @param <T>
	 *            numeric value
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param max
	 *            maximum value
	 */
	public < T extends Number & Comparable< T > > void numberMax( final Messages messages, final Severity severity,
			final String field, final T value, final T max )
	{
		valueMax( messages, severity, Key.NUMBER_MAX, field, value, max );
	}

	/**
	 * Validate a numeric field exceeds minimum value.
	 *
	 * @param <T>
	 *            numeric value
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param min
	 *            minimum value
	 */
	public < T extends Number & Comparable< T > > void numberMin( final Messages messages, final Severity severity,
			final String field, final T value, final T min )
	{
		valueMin( messages, severity, Key.NUMBER_MIN, field, value, min );
	}

	/**
	 * Validate a numeric field is between minimum and maximum values.
	 *
	 * @param <T>
	 *            numeric value
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param min
	 *            minimum value
	 * @param max
	 *            maximum value
	 */
	public < T extends Number & Comparable< T > > void numberBetween( final Messages messages, final Severity severity,
			final String field, final T value, final T min, final T max )
	{
		valueBetween( messages, severity, Key.NUMBER_BETWEEN, field, value, min, max );
	}

	/**
	 * Validate a date field is within maximum value.
	 *
	 * @param <T>
	 *            date/time value
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param max
	 *            maximum value
	 */
	public < T extends TemporalAccessor & Comparable< T > > void dateMax( final Messages messages,
			final Severity severity, final String field, final T value, final T max )
	{
		valueMax( messages, severity, Key.DATE_MAX, field, value, max );
	}

	/**
	 * Validate a date field exceeds minimum value.
	 *
	 * @param <T>
	 *            date/time value
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param min
	 *            minimum value
	 */
	public < T extends TemporalAccessor & Comparable< T > > void dateMin( final Messages messages,
			final Severity severity, final String field, final T value, final T min )
	{
		valueMin( messages, severity, Key.DATE_MIN, field, value, min );
	}

	/**
	 * Validate a date field is between minimum and maximum values.
	 *
	 * @param <T>
	 *            date/time value
	 * @param messages
	 *            store for validation messages
	 * @param severity
	 *            severity of failure
	 * @param field
	 *            field being validated
	 * @param value
	 *            value being validated
	 * @param min
	 *            minimum value
	 * @param max
	 *            maximum value
	 */
	public < T extends TemporalAccessor & Comparable< T > > void dateBetween( final Messages messages,
			final Severity severity, final String field, final T value, final T min, final T max )
	{
		valueBetween( messages, severity, Key.DATE_BETWEEN, field, value, min, max );
	}

	protected < T extends Comparable< T > > void valueMax( final Messages messages, final Severity severity,
			final Key key, final String field, final T value, final T max )
	{
		if ( value != null && value.compareTo( max ) > 0 )
		{
			messages.addMessages( severity, field, getMessage( key, field, value, max ) );
		}
	}

	protected < T extends Comparable< T > > void valueMin( final Messages messages, final Severity severity,
			final Key key, final String field, final T value, final T min )
	{
		if ( value != null && value.compareTo( min ) < 0 )
		{
			messages.addMessages( severity, field, getMessage( key, field, value, min ) );
		}
	}

	protected < T extends Comparable< T > > void valueBetween( final Messages messages, final Severity severity,
			final Key key, final String field, final T value, final T min, final T max )
	{
		if ( value != null && ( value.compareTo( min ) < 0 || value.compareTo( max ) > 0 ) )
		{
			messages.addMessages( severity, field, getMessage( key, field, value, min, max ) );
		}
	}

	protected abstract String getBundle();

	protected Message getMessage( final String key, final Object... args )
	{
		return new BundleMessage( getBundle(), key, args );
	}

	protected Message getMessage( final Enum< ? > key, final Object... args )
	{
		return new EnumMessage( key, args );
	}

}
