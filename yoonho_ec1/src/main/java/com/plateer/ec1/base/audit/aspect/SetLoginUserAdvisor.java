package com.plateer.ec1.base.audit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.plateer.ec1.base.vo.BaseVo;

@Aspect
public class SetLoginUserAdvisor {

	@Around("@annotation(com.plateer.ec1.base.audit.annotation.SetLoginUser)")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		for (Object obj : joinPoint.getArgs()) {
			if (obj instanceof BaseVo) {
				BaseVo vo = (BaseVo)obj;
				vo.setSysModrId("admin");
				vo.setSysRegrId("admin");
			}
		}

		Object object = joinPoint.proceed();
		return object;
	}
}
