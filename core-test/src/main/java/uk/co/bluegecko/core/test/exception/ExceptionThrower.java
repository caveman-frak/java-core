package uk.co.bluegecko.core.test.exception;


/**
 * Lambda marker for constructors and methods that will throw an exception.
 */
@FunctionalInterface
public interface ExceptionThrower
{

	/**
	 * Marker for constructor or method.
	 *
	 * @throws Throwable
	 *             the thrown exception
	 */
	void throwException() throws Throwable;

}
