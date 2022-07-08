package com.plateer.ec1.promotion.service;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.plateer.ec1.config.exception.BusinessException;
import com.plateer.ec1.promotion.DaoMockTestConfig;
import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.CouponUseReq;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Import(DaoMockTestConfig.class) // Dao를 목 처리하기 위해 환경을 별도 설정 함. 
public class CouponServiceTest {
	@Autowired
	private CouponService couponService;
	
	@Test
	@DisplayName("쿠폰다운로드 가능여부 확인")
	public void downloadable_true() {
		CouponDownloadReq req = new CouponDownloadReq();
		req.setPrmNo(1L);
		req.setMbrNo("test01");
		
		Assertions.assertThatNoException()
		.isThrownBy(() -> couponService.couponDownload(req));
	}
	
	@Test
	@DisplayName("쿠폰다운로드 오류 확인")
	public void couonDownload_term_fail() {
		CouponDownloadReq req = new CouponDownloadReq();
		req.setPrmNo(2L);
		req.setMbrNo("test01");
		
		Assertions.assertThatThrownBy(() -> couponService.couponDownload(req))
		.isInstanceOf(BusinessException.class)
		;
	}
	
	@Test
	@DisplayName("쿠폰다운로드 오류 확인-유효성체크 회원번호")
	public void couonDownload_fail() {
		CouponDownloadReq req = new CouponDownloadReq();
		req.setPrmNo(1L);
		
		Assertions.assertThatThrownBy(() -> couponService.couponDownload(req))
		.isInstanceOf(ConstraintViolationException.class)
		.hasMessageContaining("mbrNo");
	}
	
	@Test
	@DisplayName("쿠폰다운로드 정상 확인")
	public void couonDownload_success() {
		CouponDownloadReq req = new CouponDownloadReq();
		req.setPrmNo(1L);
		req.setMbrNo("test01");
		
		Assertions.assertThatNoException()
		.isThrownBy(() -> couponService.couponDownload(req));
	}
	
	@Test
	@DisplayName("쿠폰사용 처리 오류 확인 - 유효성 체크 오류 쿠폰발급번호")
	public void couonUse_fail() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		
		Assertions.assertThatThrownBy(() -> couponService.couponUse(req))
		.isInstanceOf(ConstraintViolationException.class)
		.hasMessageContaining("cpnIssNo");
	}
	
	@Test
	@DisplayName("쿠폰사용 처리 오류 발급번호 오류 - 유효성 체크 오류 쿠폰발급번호 없는경우")
	public void couonUse_issno_fail() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		req.setMbrNo("test01");
		req.setCpnIssNo(2L);
		req.setOrdNo("O20220629000001");
		
		Assertions.assertThatThrownBy(() -> couponService.couponUse(req))
		.isInstanceOf(BusinessException.class)
		.extracting(th -> ((BusinessException)th).getMessgeId())
		.isEqualTo("promotion.coupon.use.imposible.issueno");
	}
	
	@Test
	@DisplayName("쿠폰사용 처리 ")
	public void couonUse_success() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		req.setMbrNo("test01");
		req.setCpnIssNo(1L);
		req.setOrdNo("O20220629000001");
		
		Assertions.assertThatNoException()
		.isThrownBy(() -> couponService.couponUse(req));
	}
	
	@Test
	@DisplayName("쿠폰복원 처리 오류 확인")
	public void couonRestore_fail() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		
		Assertions.assertThatThrownBy(() -> couponService.couponRestore(req))
		.isInstanceOf(ConstraintViolationException.class)
		.hasMessageContaining("cpnIssNo");
	}
	
	@Test
	@DisplayName("쿠폰복원 처리 오류 확인")
	public void couonRestore_mbrNo_fail() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		req.setCpnIssNo(6L);
		
		Assertions.assertThatThrownBy(() -> couponService.couponRestore(req))
		.isInstanceOf(ConstraintViolationException.class)
		.hasMessageContaining("mbrNo");
	}
	
	@Test
	@DisplayName("쿠폰복원 처리")
	public void couonRestore_success() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		req.setCpnIssNo(1L);
		req.setMbrNo("test01");
		

		Assertions.assertThatNoException()
		.isThrownBy(() -> couponService.couponRestore(req));
	}
	
	@Test
	@DisplayName("쿠폰복원 처리 오류")
	public void couonRestore_cpnIssue_fail() {
		CouponUseReq req = new CouponUseReq();
		req.setPrmNo(1L);
		req.setCpnIssNo(2L);
		req.setMbrNo("test01");
		

		Assertions.assertThatNoException()
		.isThrownBy(() -> couponService.couponRestore(req));
	}
	
}
