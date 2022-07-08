package com.plateer.ec1.base.LogTrace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TraceStatus {
	private TraceId traceLog;
	private Long startTimeMs;
	private String msg;
}
