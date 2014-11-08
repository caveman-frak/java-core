/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model.base.extension;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.bluegecko.core.model.Model;
import uk.co.bluegecko.core.model.base.AbstractData;
import uk.co.bluegecko.core.model.extension.Extended;
import uk.co.bluegecko.core.model.extension.Extension;


/**
 * A base implementation of a data entity supporting extensions.
 *
 * @param <K>
 *            the data class
 * @param <M>
 *            the model class
 */
@SuppressWarnings( "null" )
public abstract class AbstractExtendedData< K extends Comparable< K >, M extends Model > extends AbstractData< K >
		implements Extended< M >, Model
{

	private static final long serialVersionUID = -1264009544621267181L;
	private final List< Extension< M >> extensions;

	/**
	 * Create an extended data entity, passing in the list of supported extensions.
	 *
	 * @param id
	 *            the underlying identifier
	 * @param extensions
	 *            list of extensions to add to the model
	 */
	public AbstractExtendedData( final K id, final List< Extension< M >> extensions )
	{
		super( id );

		this.extensions = extensions;
	}

	/**
	 * Create an extended data entity.
	 *
	 * @param id
	 *            the underlying identifier
	 */
	public AbstractExtendedData( final K id )
	{
		this( id, new ArrayList<>() );
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
