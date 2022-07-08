package com.plateer.ec1.claim.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AplyCnclCcd {
	APLY("APLY"),
	CNCL("CNCL")
	;
	
	private final String code;
	
	public AplyCnclCcd reverse() {
		if(this == APLY) {
			return CNCL;
		}
		return APLY;
	}
	
	public static AplyCnclCcd get(String code) {
		return Arrays.stream(AplyCnclCcd.values())
        		.filter((e) -> e.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
	}
}
