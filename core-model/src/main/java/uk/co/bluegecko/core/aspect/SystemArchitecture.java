package uk.co.bluegecko.core.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Aspect to define various common point-cuts related to the system architecture.
 */
@Aspect
public class SystemArchitecture
{

	/**
	 * A join point is in the web layer if the method is defined
	 * in a type in the uk.co.bluegecko.web package or any sub-package
	 * under that.
	 */
	@Pointcut( "within(uk.co.bluegecko..resource..*)" )
	public void inWebLayer()
	{}

	/**
	 * A join point is in the service layer if the method is defined
	 * in a type in the uk.co.bluegecko.service package or any sub-package
	 * under that.
	 */
	@Pointcut( "within(uk.co.bluegecko..service..*)" )
	public void inServiceLayer()
	{}

	/**
	 * A join point is in the data access layer if the method is defined
	 * in a type in the uk.co.bluegecko.dao package or any sub-package
	 * under that.
	 */
	@Pointcut( "within(uk.co.bluegecko..dao..*)" )
	public void inDataAccessLayer()
	{}

	/**
	 * A business service is the execution of any method defined on a service
	 * interface. This definition assumes that interfaces are placed in the
	 * "service" package, and that implementation types are in sub-packages.
	 *
	 * If you group service interfaces by functional area (for example,
	 * in packages uk.co.bluegecko.abc.service and uk.co.bluegecko.def.service) then
	 * the pointcut expression "execution(* uk.co.bluegecko..service.*.*(..))"
	 * could be used instead.
	 *
	 * Alternatively, you can write the expression using the 'bean'
	 * PCD, like so "bean(*Service)". (This assumes that you have
	 * named your Spring service beans in a consistent fashion.)
	 */
	@Pointcut( "execution(* uk.co.bluegecko..service.*.*(..)) || target(uk.co.bluegecko.core.service.Service)" )
	public void businessService()
	{}

	/**
	 * A data access operation is the execution of any method defined on a
	 * dao interface. This definition assumes that interfaces are placed in the
	 * "dao" package, and that implementation types are in sub-packages.
	 */
	@Pointcut( "execution(* uk.co.bluegecko.dao.*.*(..))" )
	public void dataAccessOperation()
	{}

}
