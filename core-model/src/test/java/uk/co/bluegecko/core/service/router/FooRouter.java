/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.router;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component
public class FooRouter extends Router< RouteFoo >
{

	/**
	 * @param implementations
	 *            list of providers
	 */
	@Autowired( required = false )
	public FooRouter( final List< RouteFoo > implementations )
	{
		super( implementations );
	}

}
