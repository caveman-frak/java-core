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
 * Test Model class Bar.
 */
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class BarModelBase extends AbstractExtendedModel< BarModel > implements BarModel
{

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new bar model.
	 */
	public BarModelBase()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.base.extension.ExtendedModel#setExtensions(java.util.List)
	 */
	@Override
	@Autowired
	public void setExtensions( final List< Extension< BarModel >> extensions )
	{
		super.setExtensions( extensions );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.extension.BarModel#bar()
	 */
	@Override
	public void bar()
	{
		System.out.println( "Hello World!" );
	}

}
