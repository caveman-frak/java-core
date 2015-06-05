package uk.co.bluegecko.core.aspect.tracker;


import java.util.Locale;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import ch.qos.cal10n.MessageConveyor;


/**
 * Log entry and exit of method invocation details.
 */
@Aspect
@Component
public class TrackingAspect
{

	/** Log messages. */
	@BaseName( "uk.co.bluegecko.core.aspect.tracker.TrackingAspect$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		/** Track entering method */
		ENTER,
		/** Track exiting method */
		EXIT,
		/** Track exiting via throwing exception */
		THROWN
	}

	private final LocLogger logger;

	/**
	 * Default constructor.
	 */
	public TrackingAspect()
	{
		logger = new LocLoggerFactory( new MessageConveyor( Locale.ENGLISH ) ).getLocLogger( TrackingAspect.class );
	}

	/**
	 * Log entry of the method.
	 *
	 * @param jp
	 *            wrapped method join point
	 */
	@Before( "trackedMethods()" )
	public void trackEntry( final JoinPoint jp )
	{
		if ( logger.isDebugEnabled() )
		{
			logger.debug( Log.ENTER, jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName() );
		}
	}

	/**
	 * Log exit of the method.
	 *
	 * @param jp
	 *            wrapped method join point
	 */
	@AfterReturning( "trackedMethods()" )
	public void trackExit( final JoinPoint jp )
	{
		if ( logger.isDebugEnabled() )
		{
			logger.debug( Log.EXIT, jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName() );
		}
	}

	/**
	 * Log entry of the method.
	 *
	 * @param jp
	 *            wrapped method join point
	 * @param ex
	 *            the exception that caused method termination
	 */
	@AfterThrowing( pointcut = "trackedMethods()", throwing = "ex" )
	public void trackThrowing( final JoinPoint jp, final Throwable ex )
	{
		if ( logger.isDebugEnabled() )
		{
			logger.debug( Log.THROWN, jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName(), ex
					.getClass().getSimpleName() );
		}
	}

	/**
	 * Point-cut to apply to all methods we want tracked.
	 */
	@Pointcut( "annotatedMethod() || businessServiceMethod()" )
	public void trackedMethods()
	{}

	/**
	 * Point-cut to apply to methods annotated with @Tracked.
	 */
	@Pointcut( "@annotation(uk.co.bluegecko.core.aspect.tracker.Tracked)" )
	public void annotatedMethod()
	{}

	/**
	 * Point-cut to apply to business service methods (defined by the system architecture).
	 */
	@Pointcut( "uk.co.bluegecko.core.aspect.SystemArchitecture.businessService()" )
	public void businessServiceMethod()
	{}

}