package uk.co.bluegecko.core.server.test;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import uk.co.bluegecko.core.ApplicationConfig;


@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
@SpringApplicationConfiguration( classes = ApplicationConfig.class )
@WebIntegrationTest( randomPort = true, value =
	{ "server.port=0", "management.port=0", "shell.ssh.port=0" } )
public abstract class AbstractWebTest
{

	@Autowired
	private WebApplicationContext context;

	@Value( "${local.server.port}" )
	private int httpPort;
	@Value( "${local.management.port}" )
	private int jmxPort;

	protected WebApplicationContext getContext()
	{
		return context;
	}

	protected int getHttpPort()
	{
		return httpPort;
	}

	protected int getJmxPort()
	{
		return jmxPort;
	}

}
