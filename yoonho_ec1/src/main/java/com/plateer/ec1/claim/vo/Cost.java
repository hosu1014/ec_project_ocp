package com.plateer.ec1.claim.vo;

import com.plateer.ec1.claim.enums.CostType;

import lombok.Data;

@Data
public class Cost {
	private CostType costType;
	private DeliveryPolicy deliveryPolicy;
}
