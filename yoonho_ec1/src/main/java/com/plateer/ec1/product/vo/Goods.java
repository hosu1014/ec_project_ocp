package com.plateer.ec1.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Goods {
	private String goodsNo;
	private String goodsNm;
	private String itemNo;
	private String itemNm;
	private String goodsTpCd;
	private Long salePrc;
	private Long prmPrc;
	private String prgsStatCd;
	private String entrNo;
}
