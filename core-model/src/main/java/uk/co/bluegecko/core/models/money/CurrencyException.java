package uk.co.bluegecko.core.models.money;


import uk.co.bluegecko.core.exceptions.BaseRuntimeException;


/**
 * Exception for money and currency related exceptions.
 *
 * @author tim
 *
 */
public class CurrencyException extends BaseRuntimeException
{

	private static final long serialVersionUID = -5907082187250387777L;

	/**
	 * Create a new exception.
	 *
	 * @param messageKey
	 *            the exception reason
	 * @param args
	 *            any message parameters
	 */
	public CurrencyException( final Money.Log messageKey, final Object... args )
	{
		super( messageKey, args );
	}

}
