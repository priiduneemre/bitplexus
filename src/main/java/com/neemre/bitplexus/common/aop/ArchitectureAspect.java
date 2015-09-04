package com.neemre.bitplexus.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("architectureAspect")
public class ArchitectureAspect {

	@Pointcut("within(com.neemre.bitplexus.frontend.controller..*) "
			+ "|| within(com.neemre.bitplexus.backend.crypto.event.*Listener)")
	public final void inControllerLayer() {}

	@Pointcut("within(com.neemre.bitplexus.common.dto..*)")	
	public final void inDtoLayer() {}

	@Pointcut("within(com.neemre.bitplexus.backend.service..*)")
	public final void inServiceLayer() {}

	@Pointcut("within(com.neemre.bitplexus.backend.model..*)")
	public final void inEntityLayer() {}

	@Pointcut("within(com.neemre.bitplexus.backend.data..*)")
	public final void inDataAccessLayer() {}

	@Pointcut("within(com.neemre.bitplexus.backend.crypto.NodeWrapperResolver) "
			+ "|| within(com.neemre.bitplexus.backend.crypto.adapter..*)"
			+ "|| within(com.neemre.bitplexus.backend.crypto.event.NetworkEventListenerRegistrar)")
	public final void inHelperLayer() {}

	@Pointcut("within(com.neemre.bitplexus.common.util..*)")
	public final void inUtilityLayer() {}

	@Pointcut("inControllerLayer() || inDtoLayer() || inServiceLayer() || inEntityLayer() "
			+ "|| inDataAccessLayer() || inHelperLayer() || inUtilityLayer()")
	public final void inAnyLayer() {}

	@Pointcut("execution(* com.neemre.bitplexus.frontend.controller..*.*(..)) "
			+ "|| execution(* com.neemre.bitplexus.backend.crypto.event.*Listener.*(..))")
	public final void controllerOperation() {}

	@Pointcut("execution(* com.neemre.bitplexus.common.dto..*.*(..))")
	public final void dtoOperation() {}

	@Pointcut("execution(* com.neemre.bitplexus.backend.service..*.*(..))")
	public final void serviceOperation() {}

	@Pointcut("execution(* com.neemre.bitplexus.backend.model..*.*(..))")
	public final void entityOperation() {}

	@Pointcut("execution(* com.neemre.bitplexus.backend.data..*.*(..))")
	public final void dataAccessOperation() {}

	@Pointcut("execution(* com.neemre.bitplexus.backend.crypto.NodeWrapperResolver.*(..)) "
			+ "|| execution(* com.neemre.bitplexus.backend.crypto.adapter..*.*(..))"
			+ "|| execution(* com.neemre.bitplexus.backend.crypto.event.NetworkEventListenerRegistrar.*(..))")
	public final void helperOperation() {}

	@Pointcut("execution(* com.neemre.bitplexus.common.util..*.*(..))")
	public final void utilityOperation() {}

	@Pointcut("controllerOperation() || dtoOperation() || serviceOperation() || entityOperation() "
			+ "|| dataAccessOperation() || helperOperation() || utilityOperation()")
	public final void anyOperation() {}
}