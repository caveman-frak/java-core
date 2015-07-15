package uk.co.bluegecko.core.server.filter.swagger;


import io.swagger.core.filter.SwaggerSpecFilter;
import io.swagger.model.ApiDescription;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * The rules are maintained in simple map with key as path and a boolean value
 * indicating given path is secure or not. For method level security the key is
 * combination of http method and path.
 *
 * If the resource or method is secure then it can only be viewed using a
 * secured api key
 *
 * Note: Objective of this class is not to provide fully functional
 * implementation of authorization filter. This is only a sample demonstration
 * how API authorization filter works.
 *
 */
@SuppressWarnings(
	{ "javadoc", "deprecation" } )
public class ApiAuthorizationFilter implements SwaggerSpecFilter
{

	static Logger logger = LoggerFactory.getLogger( ApiAuthorizationFilter.class );

	@Override
	public boolean isOperationAllowed( final Operation operation, final ApiDescription api,
			final Map< String, List< String >> params, final Map< String, String > cookies,
			final Map< String, List< String >> headers )
	{
		if ( !api.getMethod().equals( "get" ) || api.getPath().startsWith( "/store" ) ) { return checkKey( params,
				headers ); }
		return true;
	}

	@Override
	public boolean isParamAllowed( final Parameter parameter, final Operation operation, final ApiDescription api,
			final Map< String, List< String >> params, final Map< String, String > cookies,
			final Map< String, List< String >> headers )
	{
		return true;
	}

	@Override
	public boolean isPropertyAllowed( final Model model, final Property property, final String propertyName,
			final Map< String, List< String >> params, final Map< String, String > cookies,
			final Map< String, List< String >> headers )
	{
		return true;
	}

	public boolean checkKey( final Map< String, List< String >> params, final Map< String, List< String >> headers )
	{
		String keyValue = null;
		if ( params.containsKey( "api_key" ) )
		{
			keyValue = params.get( "api_key" ).get( 0 );
		}
		else
		{
			if ( headers.containsKey( "api_key" ) )
			{
				keyValue = headers.get( "api_key" ).get( 0 );
			}
		}
		if ( "special-key".equals( keyValue ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
