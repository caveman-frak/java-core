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
	{ "server.port=0", "management.port=0", "shell.ssh.port=0", "shell.telnet.port=0" } )
public abstract class AbstractWebTest implements ServerPorts
{

	@Autowired
	private WebApplicationContext context;

	@Value( "${local.server.port}" )
	private int httpPort;
	@Value( "${local.management.port}" )
	private int jmxPort;
	// @Value( "${local.crash.ssh.port}" )
	private int sshPort;
	// @Value( "${local.crash.telnet.port}" )
	private int telnetPort;

	protected WebApplicationContext getContext()
	{
		return context;
	}

	@Override
	public int getHttpPort()
	{
		return httpPort;
	}

	@Override
	public int getJmxPort()
	{
		return jmxPort;
	}

	@Override
	public int getSshPort()
	{
		return sshPort;
	}

	@Override
	public int getTelnetPort()
	{
		return telnetPort;
	}

}
