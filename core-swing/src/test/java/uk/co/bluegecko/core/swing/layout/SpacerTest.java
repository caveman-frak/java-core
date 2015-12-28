package uk.co.bluegecko.core.swing.layout;


import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class SpacerTest
{

	private Spacer spacer;
	private final Dimension size = new Dimension( 100, 20 );

	@Test
	public final void testSpacerCtor_Ints()
	{
		spacer = new Spacer( 100, 20 );
		assertEquals( "preferred", size, spacer.getPreferredSize() );
		assertEquals( "minimum", size, spacer.getMinimumSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testSpacerCtor_Dimension()
	{
		spacer = new Spacer( size );
		assertEquals( "preferred", size, spacer.getPreferredSize() );
		assertEquals( "minimum", size, spacer.getMinimumSize() );
		assertEquals( "maximum", size, spacer.getMaximumSize() );
	}

	@Test
	public final void testScaleUp()
	{
		spacer = new Spacer( size );
		assertEquals( new Dimension( 150, 30 ), spacer.scale( 1.5f ).getPreferredSize() );
	}

	@Test
	public final void testScaleDown()
	{
		spacer = new Spacer( size );
		assertEquals( new Dimension( 50, 10 ), spacer.scale( 0.5f ).getPreferredSize() );
	}

}
