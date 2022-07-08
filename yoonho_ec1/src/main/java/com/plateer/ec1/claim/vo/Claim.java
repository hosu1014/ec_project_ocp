package com.plateer.ec1.claim.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.plateer.ec1.claim.enums.ClaimType;

import lombok.Data;

@Data
public class Claim {
	private ClaimType claimType;
	@Valid
	@Size(min=1)
	private List<DeliveryGroup> deliveryGroup;
	private List<OrderBenefit> orderBenefit;
	private List<PaymentReq> payReqList;
	
	
}
