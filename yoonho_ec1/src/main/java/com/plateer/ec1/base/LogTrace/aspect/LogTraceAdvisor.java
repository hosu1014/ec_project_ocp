package com.plateer.ec1.base.LogTrace.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.plateer.ec1.base.LogTrace.TraceLog;
import com.plateer.ec1.base.LogTrace.TraceStatus;

import lombok.RequiredArgsConstructor;

@Aspect
@RequiredArgsConstructor
public class LogTraceAdvisor {
	private final TraceLog traceLog;

	@Around("@annotation(com.plateer.ec1.base.LogTrace.annotation.LogTrace)")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus traceStatus = null;
		try {
			String methodName = joinPoint.getSignature().toShortString();
			traceStatus = traceLog.begin(methodName);
			
			Object object = joinPoint.proceed();
			
			traceLog.end(traceStatus);
			return object;
		} catch (Exception e) {
			traceLog.exception(traceStatus, e);
			throw e;
		}
	}
}
