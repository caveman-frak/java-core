package uk.co.bluegecko.core.test.cucumber;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/**
 * Profile annotation for cucumber related code.
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
@Profile( "cucumber" )
@Component
public @interface Cucumber
{

}
