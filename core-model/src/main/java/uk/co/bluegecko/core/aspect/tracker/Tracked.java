package uk.co.bluegecko.core.aspect.tracker;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This method should be tracked for logging.
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
@Inherited
@Documented
public @interface Tracked
{

}
