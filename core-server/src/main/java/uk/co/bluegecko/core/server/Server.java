package uk.co.bluegecko.core.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SuppressWarnings( "javadoc" )
@SpringBootApplication
public class Server
{

	public static void main( final String[] args ) throws Exception
	{
		SpringApplication.run( Server.class, args );
	}
}
