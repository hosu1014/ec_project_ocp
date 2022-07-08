package com.plateer.ec1.promotion.model;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.plateer.ec1.base.vo.BaseVo;
import com.plateer.ec1.util.LocalDateUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class CcCpnBase extends BaseVo {
	private Long prmNo;
	private String cpnKindCd;
	private String degrCcd;
	private String dcSvCcd;
	private String mdaGb;
	private String entChnGb;
	private String dwlAvlStrtDd;
	private String dwlAvlEndDd;
	private int dwlPsbCnt;
	private int psnDwlPsbCnt;
	private String dwlAvlPlc;
	private String issWayCcd;
	private String certCd;
	private double ourChrgRt;
	private double entrChrgRt;

	@SuppressWarnings("deprecation")
	public boolean termCheck() {
		if(StringUtils.isEmpty(this.getDwlAvlEndDd()) ||
				StringUtils.isEmpty(this.getDwlAvlStrtDd())) return false;
		
		LocalDate today = LocalDate.now();
		LocalDate downloadStartDate = LocalDateUtil.of(this.getDwlAvlStrtDd());
		LocalDate downloadEndDate = LocalDateUtil.of(this.getDwlAvlEndDd());
		
		return (today.isBefore(downloadEndDate) || today.isEqual(downloadEndDate))
				&& (today.isAfter(downloadStartDate) || today.isEqual(downloadStartDate));
	}
}
