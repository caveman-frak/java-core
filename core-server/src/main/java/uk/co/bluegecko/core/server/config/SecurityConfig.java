package uk.co.bluegecko.core.server.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SuppressWarnings( "javadoc" )
@Configuration
@EnableWebSecurity
@Order( Ordered.LOWEST_PRECEDENCE - 6 )
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure( final HttpSecurity http ) throws Exception
	{
		http.authorizeRequests().antMatchers( "/", "/api/swagger.json" ).permitAll().antMatchers( "/api/**" )
				.hasRole( "USER" ).anyRequest().authenticated();
		http.httpBasic().realmName( "My API" );
	}

	@Override
	protected void configure( final AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception
	{
		authenticationManagerBuilder.inMemoryAuthentication().withUser( "test" ).password( "test123" ).roles( "USER" );
	}
}
