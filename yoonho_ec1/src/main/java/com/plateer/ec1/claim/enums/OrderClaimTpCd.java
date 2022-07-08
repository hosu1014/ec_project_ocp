package com.plateer.ec1.claim.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderClaimTpCd {
	ORDER("O"), 
	CANCEL("C"),
	RETURN("R"),
	EXCHANGE("X"),
	RETURN_CANCEL("RC"),
	EXCHANGE_CANCEL("XC")
	;
	
	private final String code;
	
	public static OrderClaimTpCd get(String code) {
		return Arrays.stream(OrderClaimTpCd.values())
        		.filter((e) -> e.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
	}

}
