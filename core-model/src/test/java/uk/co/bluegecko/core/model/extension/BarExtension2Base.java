/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
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
public class BarExtension2Base implements BarExtension2
{

	/**
	 *
	 */
	public BarExtension2Base()
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
