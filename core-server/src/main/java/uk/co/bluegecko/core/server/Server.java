package uk.co.bluegecko.core.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings( "javadoc" )
@RestController
@SpringBootApplication
public class Server
{

	@RequestMapping( "/" )
	public String home()
	{
		return "Hello World!";
	}

	public static void main( final String[] args ) throws Exception
	{
		SpringApplication.run( Server.class, args );
	}
}
