/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.extension;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.bluegecko.core.model.Model;
import uk.co.bluegecko.core.model.extension.Extended;
import uk.co.bluegecko.core.model.extension.Extension;


/**
 * A base implementation of an model supporting extensions.
 *
 * @param <M>
 *            the model class
 */
@SuppressWarnings( "null" )
public abstract class AbstractExtendedModel< M extends Model > implements Extended< M >, Model
{

	private final List< Extension< M >> extensions;

	/**
	 * Create an extended model, passing in the list of supported extensions.
	 *
	 * @param extensions
	 *            list of extensions to add to the model
	 */
	public AbstractExtendedModel( final List< Extension< M >> extensions )
	{
		super();

		this.extensions = extensions;
	}

	/**
	 * Create an extended model.
	 */
	public AbstractExtendedModel()
	{
		this( new ArrayList<>() );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.extension.Extended#setExtensions(java.util.List)
	 */
	@Override
	public void setExtensions( final List< Extension< M >> extensions )
	{
		this.extensions.clear();
		this.extensions.addAll( extensions );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.extension.Extended#getExtensions()
	 */
	@Override
	public List< Extension< M >> getExtensions()
	{
		return Collections.unmodifiableList( extensions );
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.extension.Extended#getExtensions(java.lang.Class)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T extends Extension< M >> T getExtension( final Class< T > klass )
	{
		for ( final Extension< M > extension : extensions )
		{
			if ( klass.isInstance( extension ) )
				return ( T ) extension;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see uk.co.bluegecko.core.model.extension.Extended#applyExtension()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public void applyExtension()
	{
		for ( final Extension< M > extension : extensions )
		{
			extension.apply( ( M ) this );
		}
	}

}
