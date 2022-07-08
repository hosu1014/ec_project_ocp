package com.plateer.ec1.promotion.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.CouponPromotion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CouponBaseTest {
	
	@Autowired
	private CcCpnBaseDao cpnBaseDao;
	
	@Test
	void CpnBaseTest() {
		CouponDownloadReq req = new CouponDownloadReq();
		req.setPrmNo(1L);
		CouponPromotion cp = cpnBaseDao.getCouponBase(req);
		
		Assertions.assertThat(cp.getCcCpnBase().getPsnDwlPsbCnt())
		.as("Coupon %s의 개인별 다운로드 가능 개수 확인", cp.getCcPrmBase().getPrmNm())
		.isEqualTo(5);
	}
}
