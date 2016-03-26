package uk.co.bluegecko.core.server.tracker;


import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;


/**
 * Interface for tracker token constants and default token creator.
 *
 * @author tim
 *
 */
public interface TrackerToken
{

	/**
	 * Default encoding
	 */
	public static final String ENCODING = "UTF8";
	/**
	 * MDC logging constant for token.
	 */
	public static final String TOKEN = "X-Trace";
	/**
	 * MDC logging constant for token counter.
	 */
	public static final String COUNTER = "X-TraceCount";
	/**
	 * MDC logging constant for user name.
	 */
	public static final String USERNAME = "X-UserName";

	/** Log messages. */
	@BaseName( "uk.co.bluegecko.core.server.tracker.TrackerToken$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) })
	public enum Log
	{
		/** Log request */
		REQUEST, /** Log response */
		RESPONSE, /** Log exception response */
		ERROR;
	}

}
