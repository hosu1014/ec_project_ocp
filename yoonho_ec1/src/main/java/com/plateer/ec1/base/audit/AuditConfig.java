package com.plateer.ec1.base.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.plateer.ec1.base.audit.aspect.SetLoginUserAdvisor;

@Configuration
public class AuditConfig {
	
	@Bean
	public SetLoginUserAdvisor setLoginUserAdvisor() {
		return new SetLoginUserAdvisor();
	}
}
