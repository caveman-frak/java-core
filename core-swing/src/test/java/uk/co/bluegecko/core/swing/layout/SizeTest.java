/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import java.awt.Component;
import java.awt.Dimension;

import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class SizeTest
{

	protected static final Dimension MIN = new Dimension( 10, 20 );
	protected static final Dimension PREFERRED = new Dimension( 75, 150 );
	protected static final Dimension MAX = new Dimension( 100, 200 );
	private static final Component COMPONENT = new Component()
	{

		private static final long serialVersionUID = 1L;

		@Override
		public Dimension getMaximumSize()
		{
			return MAX;
		}

		@Override
		public Dimension getMinimumSize()
		{
			return MIN;
		}

		@Override
		public Dimension getPreferredSize()
		{
			return PREFERRED;
		}

	};

	@Test
	public void testMinSize()
	{
		final Dimension d = Size.MIN.getSize( COMPONENT );
		Assert.assertEquals( 10, d.width );
		Assert.assertEquals( 20, d.height );
	}

	@Test
	public void testPreferredSize()
	{
		final Dimension d = Size.PREFERRED.getSize( COMPONENT );
		Assert.assertEquals( 75, d.width );
		Assert.assertEquals( 150, d.height );
	}

	@Test
	public void testMaxSize()
	{
		final Dimension d = Size.MAX.getSize( COMPONENT );
		Assert.assertEquals( 100, d.width );
		Assert.assertEquals( 200, d.height );
	}

}
