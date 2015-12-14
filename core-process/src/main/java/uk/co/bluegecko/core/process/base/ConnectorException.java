package uk.co.bluegecko.core.process.base;


import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import uk.co.bluegecko.core.exceptions.BaseRuntimeException;


/**
 * Exception for {@link Connector} issues.
 *
 */
public class ConnectorException extends BaseRuntimeException
{

	private static final long serialVersionUID = -8696671605965223582L;

	/**
	 * Enum for exception message constants.
	 */
	@BaseName( "uk.co.bluegecko.core.process.base.ConnectorException$Message" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) })
	public enum Message
	{
		/**
		 * Can't push an object into a finished connector
		 */
		PUSH_ON_FINISH
	}

	/**
	 * @param messageKey
	 *            message key for message
	 * @param args
	 *            optional message arguments
	 */
	public ConnectorException( final Message messageKey, final Object... args )
	{
		super( messageKey, args );
	}

	/**
	 * @param cause
	 *            underlying cause
	 * @param messageKey
	 *            message key for message
	 * @param args
	 *            optional message arguments
	 */
	public ConnectorException( final Throwable cause, final Message messageKey, final Object... args )
	{
		super( cause, messageKey, args );
	}

}
