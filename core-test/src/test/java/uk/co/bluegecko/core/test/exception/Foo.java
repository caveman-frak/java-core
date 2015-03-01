package uk.co.bluegecko.core.test.exception;


@SuppressWarnings( "javadoc" )
public final class Foo
{

	public Foo( final boolean trigger )
	{
		if ( trigger ) { throw new IllegalStateException( "constructor" ); }
	}

	public Foo()
	{
		this( true );
	}

	public boolean foo( final boolean trigger )
	{
		if ( trigger ) { throw new IllegalStateException( "foo" ); }
		return trigger;
	}

	public void foo()
	{
		throw new IllegalStateException( "constructor" );
	}

	public void bar()
	{}

}