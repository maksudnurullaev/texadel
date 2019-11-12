package my.nmk.sb1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotVeryUsefulAspect {
	private final static Logger logger = LoggerFactory.getLogger(NotVeryUsefulAspect.class);

	
	@Pointcut("execution(public * get*(..))")
	private void anyGetMethod(){
	}

	@Pointcut("within(my.nmk.sb1.components.*)")
	private void inMyNMKPackage() {}
	
	@Pointcut("anyGetMethod() && inMyNMKPackage()")
	private void anyGetMethodInMyNMKPackage() {}
	
	@Pointcut("execution(* my.nmk.sb1.MainStarterApp.*(..))")
	private void anyMethodOfMainStarterApp() {}
	
	@Pointcut("target(my.nmk.sb1.components.DumBean)")
	private void anyMethodFromMyPackage() {}
	
	//@Before("anyGetMethodInMyNMKPackage()")
	@Before("anyMethodFromMyPackage() && @annotation(my.nmk.sb1.annotations.Described)")
	private void anyMyPointcut(JoinPoint jp) {
		logger.debug("AOP: @Described: Any method that works in DumBean class! Signature: " + jp.getSignature().getName());		
	}
	
	@AfterReturning(
			pointcut="anyMethodOfMainStarterApp()",
			returning = "retVal")
	private void doItBefore(Object retVal) {
		logger.debug("AOP:@After: any method work on Main application class, retValue: " + retVal.toString());
	}
	
	@Around("anyMethodOfMainStarterApp()")
	private Object simpleAround(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = pjp.proceed();
		logger.debug("AOP:@Around: calls! Returning value: " + retVal.toString());
		//return "Fake result #2, signature: " + pjp.getSignature();
		return retVal.toString();
	}

}
