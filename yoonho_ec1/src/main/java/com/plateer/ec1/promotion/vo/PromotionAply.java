package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder @AllArgsConstructor
public class PromotionAply {
	private Long prmNo;
	private String prmNm;
	private String degrCcd;
	private String cpnKindCd;
	private Long cpnIssNo;
	private Long ordBnfAmt;
	private String maxBnfYn;
	private String cpnAplyYn; //쿠폰적용여부
	private String anotherGoodsAplyYn;
	
	
	public PromotionAply() {
		this.setMaxBnfYn(Constants.N);
		this.setCpnAplyYn(Constants.N);
		this.setAnotherGoodsAplyYn(Constants.N);
	}
	
}
