package com.plateer.ec1.promotion.vo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plateer.ec1.base.vo.BaseVo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponDownloadReq extends BaseVo{
	@NotNull
	private Long prmNo;
	@NotNull
	private String mbrNo;
	private Long cpnIssNo;
	
	@JsonIgnore
	public String getKey() {
		return this.getPrmNo().toString() + this.getMbrNo();
	}
}
