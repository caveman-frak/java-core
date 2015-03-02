/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.service.base.provider;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.service.base.provider.Provider;


/**
 *
 */
@Component
public class FooProvider extends Provider< ProviderFoo >
{

	/**
	 * @param implementations
	 *            list of providers
	 */
	@Autowired( required = false )
	public FooProvider( final List< ProviderFoo > implementations )
	{
		super( implementations );
	}

}
