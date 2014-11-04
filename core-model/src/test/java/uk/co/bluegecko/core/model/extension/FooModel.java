/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.extension;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import uk.co.bluegecko.core.model.base.extension.AbstractExtendedModel;


/**
 * Test Model class Foo.
 */
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class FooModel extends AbstractExtendedModel< FooModel >
{

	/**
	 * Create a new foo model.
	 *
	 * @param extensions
	 *            list of supported extensions
	 *
	 */
	@Autowired
	public FooModel( final List< Extension< FooModel >> extensions )
	{
		super( extensions );
	}

}
