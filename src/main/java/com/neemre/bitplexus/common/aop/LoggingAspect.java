package com.neemre.bitplexus.common.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.neemre.bitplexus.common.Constants;

@Aspect
@Component("loggingAspect")
public class LoggingAspect {

	@Before("ArchitectureAspect.controllerOperation()")
	public void logControllerMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).info(">> {}({}): execution started", methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames));
	}

	@AfterReturning(pointcut = "ArchitectureAspect.controllerOperation()", returning = "result")
	public void logControllerMethodExit(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).info("<< {}({}): execution completed" + (result 
				== null ? "" : ", returning result as: '" + result.toString() + "'"), methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames));
	}

	@AfterThrowing(pointcut = "ArchitectureAspect.controllerOperation()", throwing = "e")
	public void logControllerMethodException(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).error("<< {}({}): an exception was caught by "
				+ "the application's fault barrier, message was: '{}'", methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames), 
				e.getMessage());
	}

	@Before("ArchitectureAspect.dtoOperation()")
	public void logDtoMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).info(">> {}({}): execution started", methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames));
	}

	@AfterReturning(pointcut = "ArchitectureAspect.dtoOperation()", returning = "result")
	public void logDtoMethodExit(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).info("<< {}({}): execution completed" + (result 
				== null ? "" : ", returning result as: '" + result.toString() + "'"), methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames));
	}

	@Before("ArchitectureAspect.serviceOperation()")
	public void logServiceMethodEntry(JoinPoint joinPoint) {
		logControllerMethodEntry(joinPoint);
	}

	@AfterReturning(pointcut = "ArchitectureAspect.serviceOperation()", returning = "result")
	public void logServiceMethodExit(JoinPoint joinPoint, Object result) {
		logControllerMethodExit(joinPoint, result);
	}

	@Before("ArchitectureAspect.entityOperation()")
	public void logEntityMethodEntry(JoinPoint joinPoint) {
		logDtoMethodEntry(joinPoint);
	}

	@AfterReturning(pointcut = "ArchitectureAspect.entityOperation()", returning = "result")
	public void logEntityMethodExit(JoinPoint joinPoint, Object result) {
		logDtoMethodExit(joinPoint, result);
	}

	@Before("ArchitectureAspect.dataAccessOperation()")
	public void logDataAccessMethodEntry(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).info(">> {}({}): execution started", methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames));
	}

	@AfterReturning(pointcut = "ArchitectureAspect.dataAccessOperation()", returning = "result")
	public void logDataAccessMethodExit(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		List<String> argTypeNames = getClassNames(Arrays.asList(joinPoint.getArgs()));
		getLogger(joinPoint.getTarget().getClass()).info("<< {}({}): execution completed" + (result 
				== null ? "" : ", returning result as: '" + result.toString() + "'"), methodName, 
				Joiner.on(Constants.STRING_COMMA + Constants.STRING_SPACE).join(argTypeNames));
	}

	@Before("ArchitectureAspect.helperOperation()")
	public void logHelperMethodEntry(JoinPoint joinPoint) {
		logDtoMethodEntry(joinPoint);
	}

	@AfterReturning(pointcut = "ArchitectureAspect.helperOperation()", returning = "result")
	public void logHelperMethodExit(JoinPoint joinPoint, Object result) {
		logDtoMethodExit(joinPoint, result);
	}

	@Before("ArchitectureAspect.utilityOperation()")
	public void logUtilityMethodEntry(JoinPoint joinPoint) {
		logDtoMethodEntry(joinPoint);
	}

	@AfterReturning(pointcut = "ArchitectureAspect.utilityOperation()", returning = "result")
	public void logUtilityMethodExit(JoinPoint joinPoint, Object result) {
		logDtoMethodExit(joinPoint, result);
	}

	private Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	private List<String> getClassNames(List<Object> objects) {
		List<String> classNames = new ArrayList<String>();
		if (objects != null) {
			for (Object object : objects) {
				if (object == null) {
					classNames.add(Constants.STRING_NULL);
				} else {
					String className = object.getClass().getSimpleName();
					if (className.equals(Constants.STRING_EMPTY)) {
						className = "AnonymousClass#" + (new Random().nextInt(9000) + 1000);
					}
					classNames.add(className);
				}
			}
		}
		return classNames;
	}
}