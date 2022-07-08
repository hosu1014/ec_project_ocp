package com.plateer.ec1.base.LogTrace;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TraceLog {
	private final String END_PREFIX = "<O-";
	private final String EX_PREFIX = "<X-";
	private final String BEGIN_PREFIX = "-->";
	
	private ThreadLocal<TraceId> traceLogHolder= new ThreadLocal<>(); 
	
	
	public TraceStatus begin(String msg) {
		TraceId tl = syncTraceLog();
		Long startTimeMs = System.currentTimeMillis();
		
		log.info("[{}] {}{}", tl.getMsgId(), addSpace(BEGIN_PREFIX, tl.getLevel()), msg);
		
		return new TraceStatus(tl, startTimeMs, msg);
	}
	
	private TraceId syncTraceLog() {
		if(traceLogHolder.get() == null) {
			traceLogHolder.set(new TraceId());
		} else {
			traceLogHolder.set(traceLogHolder.get().createNextId());
		}
		return traceLogHolder.get();
	}
	
	private String addSpace(String prefix, int level) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<level+1;i++) {
			sb.append((i==0) ? "|" : "  |");
		}
		sb.append(prefix);
		return sb.toString();
	}

	public void end(TraceStatus ts) {
		complete(ts, null);
	}
	
	public void exception(TraceStatus ts, Exception e) {
		complete(ts, e);
	}
	
	private void complete(TraceStatus ts, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
		long resultTimeMs = stopTimeMs - ts.getStartTimeMs();
		TraceId tl = ts.getTraceLog();
		
		if(e == null) {
			log.info("[{}] {}{} time={}ms", tl.getMsgId(), addSpace(END_PREFIX, tl.getLevel()), ts.getMsg(), resultTimeMs);
		} else {
			log.info("[{}] {}{} time={}ms ex={}", tl.getMsgId(), addSpace(EX_PREFIX, tl.getLevel()), ts.getMsg(), resultTimeMs, e.getMessage());
		}
		
		releaseTraceLog();
	}
	
	private void releaseTraceLog() {
		TraceId traceLog = traceLogHolder.get();
		if(traceLog.isFirstLevel()) {
			traceLogHolder.remove();
		} else {
			traceLogHolder.set(traceLog.createPreviousId());
		}
	}
}
