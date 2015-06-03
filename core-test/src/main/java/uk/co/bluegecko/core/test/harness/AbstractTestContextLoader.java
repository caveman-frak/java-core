package uk.co.bluegecko.core.test.harness;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;


/**
 * Abstract class for creating a context loader for tests.
 */
public abstract class AbstractTestContextLoader extends GenericXmlContextLoader
{

	private final Class< ? > configClass;

	protected AbstractTestContextLoader( final Class< ? > configClass )
	{
		this.configClass = configClass;
	}

	@Override
	protected void customizeContext( final GenericApplicationContext context )
	{
		try (final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext())
		{
			ctx.register( configClass );
			ctx.refresh();
			/** This is really the key */
			context.setParent( ctx );
		}
	}

}
