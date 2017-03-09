package uk.co.bluegecko.core.service.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;
import uk.co.bluegecko.core.service.common.LocaleService;


@Service
public class FooServiceImpl extends AbstractService implements FooService
{

	@BaseName( "uk.co.bluegecko.core.service.base.FooService$Foo" )
	@LocaleData(
		{ @Locale( "en" ) } )
	public enum Foo
	{
		BAR
	}

	@Autowired
	public FooServiceImpl( final ApplicationContext applicationContext, final LocaleService localeService )
	{
		super( applicationContext, localeService );
	}

	@Override
	public void bar()
	{
		getLogger().error( Foo.BAR );
	}

}
