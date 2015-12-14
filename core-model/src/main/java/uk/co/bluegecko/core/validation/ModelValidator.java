package uk.co.bluegecko.core.validation;


import uk.co.bluegecko.core.model.Messages;
import uk.co.bluegecko.core.model.Model;


/**
 * Validator for models.
 *
 * @param <T>
 *            class of model to validate
 */
public interface ModelValidator< T extends Model >
{

	/**
	 * Validate a model, add any validation errors into the passed {@link Messages} object.
	 *
	 * @param messages
	 *            store for validation errors
	 * @param model
	 *            object to validate
	 * @return updated messages
	 * @throws ValidationException
	 *             if validation errors are detected
	 */
	public Messages validate( Messages messages, T model ) throws ValidationException;

}
