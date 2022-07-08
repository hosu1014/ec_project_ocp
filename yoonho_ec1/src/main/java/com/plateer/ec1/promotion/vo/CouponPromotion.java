package com.plateer.ec1.promotion.vo;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plateer.ec1.promotion.model.CcCpnBase;
import com.plateer.ec1.promotion.model.CcPrmBase;
import com.plateer.ec1.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class CouponPromotion {
	private Long prmNo;
	private String mbrNo;
	private int totIssueCnt;
	private int mbrIssueCnt;
	private String restore;
	private CcCpnBase ccCpnBase;
	private CcPrmBase ccPrmBase;
	
	/**
	 * 테스트 코드 작성을 위한 코드 설정 
	 * @return
	 */
	@JsonIgnore
	public String getKey() {
		return this.prmNo.toString() + this.getMbrNo();
	}

	@JsonIgnore
	public boolean isDownloadable() {
		if(ObjectUtils.isEmpty(ccCpnBase)) return false;
		if(ObjectUtils.isEmpty(ccPrmBase)) return false;
		return Constants.Y.equals(ccPrmBase.getUseYn()) &&
				ccCpnBase.termCheck() && 
				checkToalCnt() &&
				checkMbrCnt();
	}
	
	@JsonIgnore
	public boolean isUseable() {
		if(ObjectUtils.isEmpty(ccCpnBase)) return false;
		if(ObjectUtils.isEmpty(ccPrmBase)) return false;
		return ccPrmBase.isUseable(ccCpnBase.getSysRegDtime());
	}
	
	@JsonIgnore
	public boolean isRestoreable() {
		return Constants.N.equals(this.getRestore()) 
				&& isUseable();
	}
	
	@JsonIgnore
	public boolean checkToalCnt() {
		if(ccCpnBase.getDwlPsbCnt() == 0) return true;
		if(ccCpnBase.getDwlPsbCnt() > this.getTotIssueCnt()) return true;
		return false;
	}
	
	@JsonIgnore
	public boolean checkMbrCnt() {
		if(ccCpnBase.getPsnDwlPsbCnt() ==0) return true;
		if(ccCpnBase.getPsnDwlPsbCnt() > this.getMbrIssueCnt()) return true;
		return false;
	}
}
