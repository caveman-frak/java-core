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
 *
 */
@Component
@Aspect
public class ProfileAspect
{

	private final LocLogger logger = new LocLoggerFactory( new MessageConveyor( Locale.ENGLISH ) )
			.getLocLogger( "MethodTimer" );

	/** Log messages. */
	@BaseName( "uk.co.bluegecko.core.aspect.profile.ProfileAspect$Log" )
	@LocaleData(
		{ @ch.qos.cal10n.Locale( "en" ) } )
	public enum Log
	{
		/** Log timer informations */
		TIMER
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
	@Around( "profilePointCut()" )
	public Object profile( final ProceedingJoinPoint pjp ) throws Throwable
	{
		// start stopwatch
		final boolean logEnabled = logger.isInfoEnabled();
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
				logger.info( Log.TIMER, pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName(), time );
			}
		}
	}

	/**
	 * Place holder for point cut.
	 */
	@Pointcut( "@annotation(uk.co.bluegecko.core.aspect.profile.Profiled)" )
	public void profilePointCut()
	{}

}
