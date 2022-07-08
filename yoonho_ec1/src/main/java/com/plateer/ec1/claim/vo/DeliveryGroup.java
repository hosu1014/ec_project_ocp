package com.plateer.ec1.claim.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.plateer.ec1.claim.enums.DeliveryType;

import lombok.Data;

@Data
public class DeliveryGroup {
	private Long deliveryGroupNo;
	private DeliveryType deliveryType;
	
	@Valid
	@Size(min=1)
	private List<ClaimProduct> productList;
	private DeliveryAddress deliveryAddress;
	private List<Cost> costList;
}
