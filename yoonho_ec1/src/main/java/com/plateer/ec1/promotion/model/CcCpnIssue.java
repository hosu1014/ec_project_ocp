package com.plateer.ec1.promotion.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.plateer.ec1.base.validator.OnCreate;
import com.plateer.ec1.base.validator.OnUpdate;
import com.plateer.ec1.base.vo.BaseVo;
import com.plateer.ec1.promotion.validator.CouponRestore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class CcCpnIssue extends BaseVo {
	@NotNull(groups= {OnCreate.class, CouponRestore.class})
	private Long prmNo;
	@NotNull(groups=OnUpdate.class)
	private Long cpnIssNo;
	@NotNull(groups=CouponRestore.class)
	private Long orgCpnIssNo;
	@NotNull(groups={OnCreate.class, CouponRestore.class})
	private String mbrNo;
	private String cpnCertNo;
	@NotNull(groups=OnUpdate.class)
	private String ordNo;
	private Timestamp cpnUseDt;
}
