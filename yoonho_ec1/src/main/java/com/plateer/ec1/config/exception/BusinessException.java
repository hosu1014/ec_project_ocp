package com.plateer.ec1.config.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 6076379594718939201L;
	private String messgeId; 
	public BusinessException() {
		super();
	}
	
	public BusinessException(String messageId) {
		super();
		this.messgeId = messageId;
	}
}
