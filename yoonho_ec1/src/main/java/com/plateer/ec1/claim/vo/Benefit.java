package com.plateer.ec1.claim.vo;

import java.util.List;

import com.plateer.ec1.claim.enums.AplyCnclCcd;
import com.plateer.ec1.claim.enums.ClaimType;

import lombok.Data;

@Data
public class Benefit implements Cloneable {
	private String ordBnfNo;
	private String aplyCnclCcd;
	private String orgOrdBnfNo;
	private Long ordBnfAmt;
	private Long ordCnclBnfAmt;
	private Promotion promotion;
	private List<OrderProduct> ordProductList;
	
	public Benefit getInsertData(ClaimType claimType) {
		Benefit bf = this.clone();
		bf.setAplyCnclCcd(AplyCnclCcd.get(this.getAplyCnclCcd()).reverse().getCode());
		bf.setOrgOrdBnfNo(this.getOrdBnfNo());
		bf.setOrdCnclBnfAmt(0L);
		
		return bf;
	}
	
		
	@Override
	protected Benefit clone() {
		Benefit bf;		
		try {
			bf = (Benefit)super.clone();

			Promotion p = this.promotion.clone();
			bf.setPromotion(p);
			
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		return bf;
	}
	
}
