package uk.co.bluegecko.core.model.base;


import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Locale;

import uk.co.bluegecko.core.convert.Convert;
import uk.co.bluegecko.core.service.common.LocalisationService;


/**
 * Base class for messages, including key and arguments.
 *
 * @param <T>
 *            type of key
 */
public abstract class LocalisedMessage< T > extends MessageBase
{

	private final String bundleName;
	private final T key;
	private final Object[] args;

	protected LocalisedMessage( final String bundleName, final T key, final Object... args )
	{
		this.bundleName = bundleName;
		this.key = key;
		this.args = args;
	}

	/**
	 * Get the message key.
	 *
	 * @return message key
	 */
	public T getKey()
	{
		return key;
	}

	/**
	 * Get the message arguments.
	 *
	 * @return message arguments
	 */
	public Object[] getArgs()
	{
		return args;
	}

	protected String getBundleName()
	{
		return bundleName;
	}

	@Override
	public String toString()
	{
		return asText( key.toString(), args );
	}

	protected Object localisedArgs( final LocalisationService localisationService, final Locale locale )
	{
		final Object[] result = Arrays.copyOf( args, args.length );
		for ( int i = 0; i < result.length; i++ )
		{
			final Object o = result[i];
			if ( o instanceof TemporalAccessor )
			{
				result[i] = Convert.fromDateTime( ( TemporalAccessor ) o );
			}
			else if ( o instanceof String && bundleName != null )
			{
				result[i] = localisationService.getRawMessage( locale, getBundleName(), ( String ) o );
			}
			else
			{
				result[i] = o;
			}
		}
		return result;

	}

}
