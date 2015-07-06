package uk.co.bluegecko.core.server;


import static uk.co.bluegecko.core.server.ServerConstants.PATH;
import static uk.co.bluegecko.core.server.ServerConstants.PORT;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Contact;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@SuppressWarnings( "javadoc" )
@WebServlet( name = "SwaggerBootstap", loadOnStartup = 2 )
public class SwaggerBootstap extends HttpServlet
{

	private static final long serialVersionUID = -4469433547593266797L;

	@Override
	public void init( final ServletConfig config ) throws ServletException
	{
		final Info info = new Info().title( "Example Server" ).version( "1.0.0" )
				.description( "This is a sample server." ).termsOfService( "http://swagger.io/terms/" )
				.contact( new Contact().name( "Caveman Frak" ).email( "apiteam@swagger.io" ) )
				.license( new License().name( "Apache 2.0" ).url( "http://www.apache.org/licenses/LICENSE-2.0.html" ) );

		final Swagger swagger = new Swagger().info( info ).host( "red-dragon.local:" + PORT ).basePath( PATH );
		swagger.tag( new Tag().name( "health" ).description( "System Health" )
				.externalDocs( new ExternalDocs( "Find out more", "http://bluegecko.co.uk" ) ) );

		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion( "2.0" );
		beanConfig.setSchemes( new String[]
			{ "http" } );
		beanConfig.setHost( "red-dragon.local:" + PORT );
		beanConfig.setBasePath( PATH );
		beanConfig.setInfo( info );
		beanConfig.setResourcePackage( "uk.co.bluegecko.core.server.resource" );
		beanConfig.configure( swagger );
		beanConfig.setScan( true );

		final ServletContext context = config.getServletContext();
		context.setAttribute( "swagger", swagger );
	}

}
