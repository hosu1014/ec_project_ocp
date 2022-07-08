package com.plateer.ec1.claim.vo;

import com.plateer.ec1.claim.enums.DeliveryFeeType;

import lombok.Data;

@Data
public class DeliveryPolicy {
	private Long policyNo;
	private DeliveryFeeType deliveryFeeType;
	private Long deliveryFee;
	private Long belowAmount;
	private Long aboveAmount;
}
