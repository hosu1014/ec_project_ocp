package com.plateer.ec1.base.LogTrace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.plateer.ec1.base.LogTrace.aspect.LogTraceAdvisor;

@Configuration
public class TraceLogConfig {
	
	@Bean
	public LogTraceAdvisor logTraceAdvisor(TraceLog traceLog) {
		return new LogTraceAdvisor(traceLog);
	}
}
