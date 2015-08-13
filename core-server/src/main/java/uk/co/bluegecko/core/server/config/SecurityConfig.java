package uk.co.bluegecko.core.server.config;


import static uk.co.bluegecko.core.server.config.ServerConstants.BASE_PATH;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_PASSWORD;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_REALM;
import static uk.co.bluegecko.core.server.config.ServerConstants.TEST_USER;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import uk.co.bluegecko.core.server.config.ServerConstants.Roles;


@SuppressWarnings( "javadoc" )
@Configuration
@EnableWebSecurity
@Order( Ordered.LOWEST_PRECEDENCE - 6 )
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure( final HttpSecurity http ) throws Exception
	{
		http.authorizeRequests().antMatchers( "/*.*", "/images/**", "/css/**", "/js/**", BASE_PATH + "/swagger.json" )
				.permitAll().antMatchers( BASE_PATH + "/**" ).hasRole( Roles.USER ).anyRequest().authenticated();
		http.httpBasic().realmName( TEST_REALM );
	}

	@Override
	protected void configure( final AuthenticationManagerBuilder authenticationManagerBuilder ) throws Exception
	{
		authenticationManagerBuilder.inMemoryAuthentication().withUser( TEST_USER ).password( TEST_PASSWORD )
				.roles( Roles.USER );
	}

}
