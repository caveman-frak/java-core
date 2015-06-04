package uk.co.bluegecko.core.aspect.profile;


import java.util.Locale;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import ch.qos.cal10n.MessageConveyor;


/**
 * Time and log method invocation details.
 */
@Aspect
@Component
public class ProfileAspect
{

	/** Log messages. */
	@BaseName( "uk.co.bluegecko.core.aspect.profile.ProfileAspect$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		/** Log timer informations */
		TIMER
	}

	private final LocLogger logger;

	/**
	 * Default constructor.
	 */
	public ProfileAspect()
	{
		logger = new LocLoggerFactory( new MessageConveyor( Locale.ENGLISH ) ).getLocLogger( ProfileAspect.class );
	}

	/**
	 * Wrap a method and log invocation time.
	 *
	 * @param pjp
	 *            wrapped method join point
	 * @return wrapped method result
	 * @throws Throwable
	 *             in case of invocation failures
	 */
	@Around( "profiledMethods()" )
	public Object profile( final ProceedingJoinPoint pjp ) throws Throwable
	{
		// start stopwatch
		final boolean logEnabled = logger.isDebugEnabled();
		final StopWatch stopWatch = logEnabled ? new StopWatch( pjp.getTarget().getClass().getSimpleName() ) : null;

		try
		{
			if ( logEnabled )
			{
				stopWatch.start( pjp.getSignature().getName() );
			}

			return pjp.proceed();
		}
		finally
		{
			if ( logEnabled )
			{
				// stop stopwatch
				stopWatch.stop();

				final long time = stopWatch.getLastTaskTimeMillis();
				logger.debug( Log.TIMER, pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName(), time );
			}
		}
	}

	/**
	 * Point-cut to apply to methods annotated with @Profiled.
	 */
	@Pointcut( "@annotation(uk.co.bluegecko.core.aspect.profile.Profiled)" )
	public void profiledMethods()
	{}
}