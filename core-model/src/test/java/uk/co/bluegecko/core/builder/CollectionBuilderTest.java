package uk.co.bluegecko.core.builder;


import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;


@SuppressWarnings( "javadoc" )
public class CollectionBuilderTest
{

	@SuppressWarnings( "unchecked" )
	@Test
	public void testList()
	{
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.list();

		builder.with( 10 ).with( 10L ).with( 10.01F ).with( 10.01D );

		final List< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testWithArray()
	{
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.list();

		builder.with( new Number[]
			{ 10, 10L, 10.01F, 10.01D } );

		final List< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testWithList()
	{
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.list();

		builder.with( Arrays.asList( 10, 10L, 10.01F, 10.01D ) );

		final List< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testSet()
	{
		final CollectionBuilder< Set< Number >, Number > builder = CollectionBuilder.set();

		builder.with( 10 ).with( 10L ).with( 10.01F ).with( 10.01D );

		final Set< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, containsInAnyOrder( 10, 10L, 10.01F, 10.01D ) );
	}

	@Test
	public void testSortedSet()
	{
		final CollectionBuilder< SortedSet< Number >, Number > builder = CollectionBuilder.sortedSet();

		builder.with( 10 ).with( 11 ).with( 12 ).with( 13 );

		final SortedSet< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 11, 12, 13 ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testQueue()
	{
		final CollectionBuilder< Queue< Number >, Number > builder = CollectionBuilder.queue();

		builder.with( 10 ).with( 10L ).with( 10.01F ).with( 10.01D );

		final Queue< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testDeque()
	{
		final CollectionBuilder< Deque< Number >, Number > builder = CollectionBuilder.deque();

		builder.with( 10 ).with( 10L ).with( 10.01F ).with( 10.01D );

		final Deque< Number > list = builder.build();
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testCopy()
	{
		final List< Number > collection = new LinkedList<>();
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.collection( collection );

		builder.with( 10 ).with( 10L );

		final CollectionBuilder< List< Number >, Number > builder2 = builder.copy();
		builder2.with( 10.01F ).with( 10.01D );

		final List< Number > list = builder2.build();
		assertThat( builder.build(), is( not( sameInstance( list ) ) ) );
		assertThat( list, is( instanceOf( LinkedList.class ) ) );
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testCopyWith()
	{
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.list();

		builder.with( 10 ).with( 10L );

		final List< Number > collection = new LinkedList<>();
		final CollectionBuilder< List< Number >, Number > builder2 = builder.copy( collection );
		builder2.with( 10.01F ).with( 10.01D );

		final List< Number > list = builder2.build();
		assertThat( list, is( sameInstance( list ) ) );
		assertThat( list, is( instanceOf( LinkedList.class ) ) );
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

	@Test
	public void testReset()
	{
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.list();

		builder.with( 10 ).with( 10L ).with( 10.01F ).with( 10.01D );
		builder.reset();

		final List< Number > list = builder.build();
		assertThat( list, hasSize( 0 ) );
	}

	@SuppressWarnings( "unchecked" )
	@Test
	public void testBuildWith()
	{
		final CollectionBuilder< List< Number >, Number > builder = CollectionBuilder.list();

		builder.with( 10 ).with( 10L ).with( 10.01F ).with( 10.01D );

		final List< Number > list = builder.build( new LinkedList<>() );
		assertThat( list, is( instanceOf( LinkedList.class ) ) );
		assertThat( list, hasSize( 4 ) );
		assertThat( list, contains( 10, 10L, 10.01F, 10.01D ) );
	}

}
