package uk.co.bluegecko.core.swing.components;


import java.awt.Dimension;
import java.awt.event.ComponentListener;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith( JMock.class )
public class SizedHelperTest
{

	private final Mockery context = new Mockery();
	private SizedHelper helper;
	protected final Dimension initialSize = new Dimension( 100, 200 );
	protected final Dimension preferredSize = new Dimension( 90, 210 );

	@Test
	public final void testWithoutConstraints()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( null ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( null ) );
				never( sized ).setSize( with( any( Dimension.class ) ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsWithinBounds()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( new Dimension( 90, 190 ) ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( new Dimension( 110, 2110 ) ) );
				never( sized ).setSize( with( any( Dimension.class ) ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsExceedsMinimumBounds()
	{
		final Dimension minimumSize = new Dimension( 110, 220 );
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( minimumSize ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( null ) );
				one( sized ).setSize( with( minimumSize ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsExceedsMaximumWidthBound()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( null ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( new Dimension( 90, 210 ) ) );
				one( sized ).setSize( with( new Dimension( 90, 200 ) ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsExceedsMaximumHeightBound()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( null ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( new Dimension( 110, 190 ) ) );
				one( sized ).setSize( with( new Dimension( 100, 190 ) ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsUsingPreferredBounds()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( SizedHelper.preferred() ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( SizedHelper.preferred() ) );
				one( sized ).setSize( with( preferredSize ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsUsingPreferredWidthBounds()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( SizedHelper.widthPreferred() ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( SizedHelper.widthPreferred() ) );
				one( sized ).setSize( with( new Dimension( 90, 200 ) ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

	@Test
	public final void testWithConstraintsUsingPreferredHeightBounds()
	{
		final Sized sized = context.mock( Sized.class );
		context.checking( new Expectations()
		{

			{
				allowing( sized ).addComponentListener( with( any( ComponentListener.class ) ) );
				atLeast( 1 ).of( sized )
						.getSize();
				will( returnValue( initialSize ) );
				allowing( sized ).getMinimumSize();
				will( returnValue( SizedHelper.heightPreferred() ) );
				allowing( sized ).getPreferredSize();
				will( returnValue( preferredSize ) );
				allowing( sized ).getMaximumSize();
				will( returnValue( SizedHelper.heightPreferred() ) );
				one( sized ).setSize( with( new Dimension( 100, 210 ) ) );
			}
		} );

		helper = new SizedHelper( sized );
		helper.notifySizeChanged();
	}

}
