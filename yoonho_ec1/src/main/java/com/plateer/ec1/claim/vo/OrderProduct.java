package com.plateer.ec1.claim.vo;

import java.security.Timestamp;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderProduct {
	@NotBlank
	private String orderNo;
	@Min(1)
	private int productSeq;
	@Min(1)
	private int processSeq;
	private int orgProcessSeq;
	private String orderClaimTpCd;
	private String orderPrgsScd;
	private String dlvRvtCcd;
	@Min(1)
	private int ordQty;
	private int cnclQty;
	private int rtgsQty;
	private Long price;
	private int dlvGroupNo;
	private List<Benefit> benefit;
	private String clmNo;
	private Timestamp directDt;
	
}
