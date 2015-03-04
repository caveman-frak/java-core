/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.router;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.service.base.router.Router;


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
