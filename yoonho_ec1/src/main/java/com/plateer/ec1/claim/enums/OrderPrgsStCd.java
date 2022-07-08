package com.plateer.ec1.claim.enums;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderPrgsStCd {
	ORDER("10"),
	CANCEL_ACCP("11"),
	CANCEL("12"),
	RTGS_ACCP("21"),
	RTGS_DIRECT("22"),
	RTGS_CONFIRM("23"),
	RTGS_COMPLETE("24"),
	DLV_DIRECT("32"), 
	DLV_CONFIRM("33"),
	DLV_SEND("34"),
	DLV_COMPLETE("35"),
	WITHDRAWAL("40")
	;
	
	private final String code;
	
	public static OrderPrgsStCd get(String code) {
		return Arrays.stream(OrderPrgsStCd.values())
        		.filter((e) -> e.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
	}
}
