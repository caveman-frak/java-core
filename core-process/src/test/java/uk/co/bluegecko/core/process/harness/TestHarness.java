package uk.co.bluegecko.core.process.harness;


import org.springframework.beans.factory.annotation.Autowired;

import uk.co.bluegecko.core.service.common.LocaleService;


@SuppressWarnings( "javadoc" )
public abstract class TestHarness extends uk.co.bluegecko.core.test.harness.TestHarness
{

	@Autowired
	private LocaleService localeService;

	protected LocaleService localeService()
	{
		return localeService;
	}

}
