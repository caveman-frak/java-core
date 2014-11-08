/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.lang;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class EqualsBuilderTest
{

	private Foo foo1;
	private Foo foo2;
	private Foo foo3;
	private Foo foo4;
	private Bar bar;

	@Before
	public final void setUp()
	{
		foo1 = new Foo( 1, "One" );
		foo2 = new Foo( 1, "One" );
		foo3 = new Foo( 2, "One" );
		foo4 = new Foo( 1, "Two" );
		bar = new Bar( 1, "One" );
	}

	@Test
	public final void testSame()
	{
		final EqualsBuilder< Foo > builder = new EqualsBuilder<>( foo1, foo1 );
		assertThat( builder.isResolved(), is( true ) );
		assertThat( builder.isSame(), is( true ) );
		assertThat( builder.isEquals(), is( true ) );
		assertThat( builder.getRhs(), is( sameInstance( foo1 ) ) );
	}

	@Test
	public final void testEqual()
	{
		final EqualsBuilder< Foo > builder = new EqualsBuilder<>( foo1, foo2 );
		assertThat( builder.isResolved(), is( false ) );
		assertThat( builder.isSame(), is( false ) );
		assertThat( builder.isEquals(), is( true ) );
		final Foo rhs = builder.getRhs();
		assertThat( rhs, is( sameInstance( foo2 ) ) );
		builder.append( foo1.getId(), rhs.getId() );
		builder.append( foo1.getText(), rhs.getText() );
		assertThat( builder.isEquals(), is( true ) );
	}

	@Test
	public final void testDifferentId()
	{
		final EqualsBuilder< Foo > builder = new EqualsBuilder<>( foo1, foo3 );
		assertThat( builder.isResolved(), is( false ) );
		assertThat( builder.isSame(), is( false ) );
		assertThat( builder.isEquals(), is( true ) );
		final Foo rhs = builder.getRhs();
		assertThat( rhs, is( sameInstance( foo3 ) ) );
		builder.append( foo1.getId(), rhs.getId() );
		builder.append( foo1.getText(), rhs.getText() );
		assertThat( builder.isEquals(), is( false ) );
	}

	@Test
	public final void testDifferentText()
	{
		final EqualsBuilder< Foo > builder = new EqualsBuilder<>( foo1, foo4 );
		assertThat( builder.isResolved(), is( false ) );
		assertThat( builder.isSame(), is( false ) );
		assertThat( builder.isEquals(), is( true ) );
		final Foo rhs = builder.getRhs();
		assertThat( rhs, is( sameInstance( foo4 ) ) );
		builder.append( foo1.getId(), rhs.getId() );
		builder.append( foo1.getText(), rhs.getText() );
		assertThat( builder.isEquals(), is( false ) );
	}

	@Test
	public final void testDifferentClass()
	{
		final EqualsBuilder< Foo > builder = new EqualsBuilder<>( foo1, bar );
		assertThat( builder.isResolved(), is( true ) );
		assertThat( builder.isSame(), is( false ) );
		assertThat( builder.isEquals(), is( false ) );
		assertThat( builder.getRhs(), is( nullValue() ) );
	}

	@Test
	public final void testUsageSame()
	{
		assertThat( usage( foo1, foo1 ), is( true ) );
	}

	@Test
	public final void testUsageEqual()
	{
		assertThat( usage( foo1, foo2 ), is( true ) );
	}

	@Test
	public final void testUsageDifferentId()
	{
		assertThat( usage( foo1, foo3 ), is( false ) );
	}

	@Test
	public final void testUsageDifferentText()
	{
		assertThat( usage( foo1, foo4 ), is( false ) );
	}

	@Test
	public final void testUsageDifferentClass()
	{
		assertThat( usage( foo1, bar ), is( false ) );
	}

	private boolean usage( final Foo lhs, final Object rhs )
	{
		final EqualsBuilder< Foo > builder = new EqualsBuilder<>( lhs, rhs );
		if ( builder.isResolved() )
			return builder.isSame();
		final Foo that = builder.getRhs();
		builder.append( lhs.getId(), that.getId() );
		builder.append( lhs.getText(), that.getText() );
		return builder.isEquals();
	}

}
