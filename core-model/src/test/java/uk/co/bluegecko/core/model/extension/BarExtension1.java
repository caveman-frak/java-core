/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.extension;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class BarExtension1 implements Extension< BarModel >
{

	/**
	 *
	 */
	public BarExtension1()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.extension.Extension#apply(uk.co.bluegecko.core.model.Model)
	 */
	@Override
	public void apply( final BarModel model )
	{
		// do nothing
	}

}
