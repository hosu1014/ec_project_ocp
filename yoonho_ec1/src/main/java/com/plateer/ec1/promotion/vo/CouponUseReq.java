package com.plateer.ec1.promotion.vo;

import javax.validation.constraints.NotNull;

import com.plateer.ec1.base.vo.BaseVo;
import com.plateer.ec1.promotion.validator.CouponRestore;
import com.plateer.ec1.promotion.validator.CouponUse;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class CouponUseReq extends BaseVo {
	@NotNull
	private Long prmNo;
	@NotNull
	private Long cpnIssNo;
	@NotNull(groups=CouponUse.class)
	private String ordNo;
	@NotNull(groups=CouponRestore.class)
	private String mbrNo;
}
