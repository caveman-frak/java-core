package uk.co.bluegecko.core.server.exceptions;


import javax.ws.rs.core.Response.Status;


/**
 * Exception that understands web http response codes.
 *
 */
public interface WebErrorStatus
{

	/**
	 * Return the web http response code for this exception.
	 * 
	 * @return response code
	 */
	public Status getStatus();

}
