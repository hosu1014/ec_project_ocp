package com.plateer.ec1.promotion;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum PrmPriodCcd {
	TERM("10"),
	BASE_DD("20")
	;
	
	private String code;
	
	private PrmPriodCcd(String code) {
		this.code = code;
	}
	
	public static PrmPriodCcd of(String code) {
		return Arrays.stream(PrmPriodCcd.values())
				.filter(c -> c.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(""));
	}
	
}
