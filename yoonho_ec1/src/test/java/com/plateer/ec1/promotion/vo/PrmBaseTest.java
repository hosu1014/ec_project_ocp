package com.plateer.ec1.promotion.vo;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.plateer.ec1.promotion.PrmPriodCcd;
import com.plateer.ec1.promotion.model.CcPrmBase;
import com.plateer.ec1.util.Constants;

public class PrmBaseTest {

	@Test
	@DisplayName("사용가능여부 테스트 기간 조건 성공")
	void test_period_ccd_10_true() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now() ));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(1L) ));
		
		Assertions.assertThat(prm.isUseable(null)).isEqualTo(true);
	}
	
	@Test
	@DisplayName("사용가능여부 테스트 기간 조건 시작일 이전 실패 ")
	void test_period_ccd_10_previos_startdate_fail() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now().plusDays(1L) ));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(1L) ));
		
		Assertions.assertThat(prm.isUseable(null)).isEqualTo(false);
	}
	
	@Test
	@DisplayName("사용가능여부 테스트 기간 조건 종료일 성공 ")
	void test_period_ccd_10_previos_enddate_true() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now() ));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now() ));
		
		Assertions.assertThat(prm.isUseable(null)).isEqualTo(true);
	}
	
	@Test
	@DisplayName("사용가능여부 테스트 기간 조건 종료일 경과 실패 ")
	void test_period_ccd_10_previos_enddate_fail() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now() ));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(-1L) ));
		
		Assertions.assertThat(prm.isUseable(null)).isEqualTo(false);
	}
	
	@Test
	@DisplayName("사용가능여부 테스트 기준일 성공 ")
	void test_period_ccd_20_true() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.BASE_DD.getCode());
		prm.setPrmStdDd(3);
		
		Timestamp regDate = Timestamp.valueOf(LocalDateTime.now());
		
		Assertions.assertThat(prm.isUseable(regDate)).isEqualTo(true);
	}

	@Test
	@DisplayName("사용가능여부 테스트 기준일 동일 성공 ")
	void test_period_ccd_20_sameDD_true() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.BASE_DD.getCode());
		prm.setPrmStdDd(3);
		
		Timestamp regDate = Timestamp.valueOf(LocalDateTime.now().plusDays(-3L).plusSeconds(1L));
		
		Assertions.assertThat(prm.isUseable(regDate)).isEqualTo(true);
	}
	
	@Test
	@DisplayName("사용가능여부 테스트 기준일 초과 실패 ")
	void test_period_ccd_20_over_DD_fail() {
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		prm.setPrmPriodCcd(PrmPriodCcd.BASE_DD.getCode());
		prm.setPrmStdDd(3);
		
		Timestamp regDate = Timestamp.valueOf(LocalDateTime.now().plusDays(-4L));
		
		Assertions.assertThat(prm.isUseable(regDate)).isEqualTo(false);
	}
	
}
