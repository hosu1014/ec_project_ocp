package com.plateer.ec1.claim.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ClaimType {
	CANCEL("C", Arrays.asList(DeliveryType.DELIVERY), Arrays.asList(OrderPrgsStCd.ORDER, OrderPrgsStCd.DLV_DIRECT), OrderPrgsStCd.CANCEL),
	RETURN("R", Arrays.asList(DeliveryType.RETURN), Arrays.asList(OrderPrgsStCd.DLV_COMPLETE), OrderPrgsStCd.RTGS_ACCP),
	EXCHANGE("X", Arrays.asList(DeliveryType.RETURN, DeliveryType.DELIVERY), Arrays.asList(OrderPrgsStCd.DLV_COMPLETE), OrderPrgsStCd.RTGS_ACCP),
	RETURN_CANCEL("RC", Arrays.asList(DeliveryType.RETURN), Arrays.asList(OrderPrgsStCd.RTGS_ACCP, OrderPrgsStCd.RTGS_DIRECT), OrderPrgsStCd.WITHDRAWAL),
	EXCHANGE_CANCEL("XC", Arrays.asList(DeliveryType.RETURN, DeliveryType.DELIVERY), Arrays.asList(OrderPrgsStCd.RTGS_ACCP, OrderPrgsStCd.RTGS_DIRECT), OrderPrgsStCd.WITHDRAWAL)
	;
	
	private final String claimType;
	private final List<DeliveryType> deliveryTypeList;
	private final List<OrderPrgsStCd> acceptPrgsStList;
	private final OrderPrgsStCd desirePrgsStCd;
	
	public static ClaimType get(String claimType) {
        return Arrays.stream(ClaimType.values())
        		.filter((e) -> e.claimType.equals(claimType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
