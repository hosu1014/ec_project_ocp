package com.plateer.ec1.claim.enums;

public enum DeliveryType {
	DELIVERY("D"), 
	RETURN("R")
	;
	
	private String code;
	
	DeliveryType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
