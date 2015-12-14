package uk.co.bluegecko.core.validation;


import java.util.Map;

import uk.co.bluegecko.core.model.Messages;


/**
 * Validator for fields or groups of fields.
 */
public interface FieldValidator
{

	/**
	 * Validate a single field, add any validation errors into the passed {@link Messages} object.
	 *
	 * @param messages
	 *            store for validation errors
	 * @param field
	 *            name of the field being validated
	 * @param value
	 *            value of the field being validated
	 * @return updated messages
	 * @throws ValidationException
	 *             if validation errors are detected
	 */
	public Messages validate( Messages messages, String field, String value ) throws ValidationException;

	/**
	 * Validate a block of fields, add any validation errors into the passed {@link Messages} object.
	 *
	 * @param messages
	 *            store for validation errors
	 * @param fieldMap
	 *            map containing the fields to be validated, keyed by name
	 * @return updated messages
	 * @throws ValidationException
	 *             if validation errors are detected
	 */
	public Messages validate( Messages messages, Map< String, Object > fieldMap ) throws ValidationException;

}
