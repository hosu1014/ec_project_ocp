package com.plateer.ec1.base.LogTrace;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TraceId {
	private String msgId;
	private int level;
	
	public TraceId() {
		this.msgId = createId();
		this.level = 0;
	}

	private String createId() {
		String randomKey = UUID.randomUUID().toString();
		return randomKey.substring(randomKey.lastIndexOf("-")+1);
	}
	
	public TraceId createNextId() {
		return new TraceId(msgId, level + 1);
	}
	
	public TraceId createPreviousId() {
		return new TraceId(msgId, level - 1);
	}
	
	public boolean isFirstLevel() {
		return this.level == 0;
	}
}
