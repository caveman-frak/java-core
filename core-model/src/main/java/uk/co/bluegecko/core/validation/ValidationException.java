package uk.co.bluegecko.core.validation;


import uk.co.bluegecko.core.exceptions.BaseRuntimeException;
import uk.co.bluegecko.core.model.Messages;


/**
 * Exception wrapping {@code Messages} for validation errors and warnings.
 *
 */
public class ValidationException extends BaseRuntimeException
{

	private static final long serialVersionUID = 2305108958257174167L;

	private final Messages messages;

	/**
	 * Create an exception wrapping the validation messages.
	 * 
	 * @param messages
	 *            validation messages
	 */
	public ValidationException( final Messages messages )
	{
		super( messages.getSeverity() );
		this.messages = messages;
	}

	/**
	 * Return the wrapped validation messages.
	 * 
	 * @return validation messages
	 */
	public Messages getMessages()
	{
		return messages;
	}

}
