package com.plateer.ec1.promotion.enums;

import java.util.Arrays;

public enum CpnKindCd {
	PRD_CUP("PC"),
	DUP_CUP("DC"),
	CART_CUP("CC");
	
	String code;
	
	CpnKindCd(String code){
		this.code = code;
	}
	
	public static CpnKindCd get(String code) {
		return Arrays.stream(CpnKindCd.values())
        		.filter((e) -> e.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
	}
}
