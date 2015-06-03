package uk.co.bluegecko.core.aspect.profile;


import org.springframework.stereotype.Component;


@SuppressWarnings( "javadoc" )
@Component
public class FooBase implements Foo
{

	@Override
	@Profiled
	public void bar()
	{
		System.out.println( ">>> bar()" );
	}

}
