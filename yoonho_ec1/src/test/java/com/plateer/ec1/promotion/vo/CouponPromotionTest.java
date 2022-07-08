package com.plateer.ec1.promotion.vo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.plateer.ec1.promotion.PrmPriodCcd;
import com.plateer.ec1.promotion.model.CcCpnBase;
import com.plateer.ec1.promotion.model.CcPrmBase;
import com.plateer.ec1.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CouponPromotionTest {
	
	@Test
	@DisplayName("개인 다운로드 가능한 수 테스트")
	void check_mbr_download_cnt_test_success() {
		CouponPromotion vo = new CouponPromotion();
		vo.setTotIssueCnt(2);
		vo.setMbrIssueCnt(1);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setPsnDwlPsbCnt(2);
		cpn.setDwlPsbCnt(2);
		
		vo.setCcCpnBase(cpn);
		
		Assertions.assertThat(vo.checkMbrCnt()).isEqualTo(true);
	}
	
	@Test
	@DisplayName("개인 다운로드 가능한 초과건 테스트")
	void check_mbr_download_cnt_over_test_success() {
		CouponPromotion vo = new CouponPromotion();
		vo.setTotIssueCnt(2);
		vo.setMbrIssueCnt(2);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setPsnDwlPsbCnt(2);
		cpn.setDwlPsbCnt(2);
		
		vo.setCcCpnBase(cpn);
		
		Assertions.assertThat(vo.checkMbrCnt()).isEqualTo(false);
	}
	
	@Test
	@DisplayName("총 다운로드 가능한 수 테스트")
	void check_tot_download_cnt_test_success() {
		CouponPromotion vo = new CouponPromotion();
		vo.setTotIssueCnt(2);
		vo.setMbrIssueCnt(0);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setDwlPsbCnt(3);
		cpn.setPsnDwlPsbCnt(2);
		
		vo.setCcCpnBase(cpn);
		
		Assertions.assertThat(vo.checkToalCnt()).isEqualTo(true);
	}
	
	@Test
	@DisplayName("총 다운로드 가능한 초과건 테스트")
	void check_tot_download_cnt_over_test_success() {
		CouponPromotion vo = new CouponPromotion();
		vo.setTotIssueCnt(2);
		vo.setMbrIssueCnt(1);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setDwlPsbCnt(2);
		cpn.setPsnDwlPsbCnt(2);
		
		vo.setCcCpnBase(cpn);
		
		Assertions.assertThat(vo.checkToalCnt()).isEqualTo(false);
	}
	
	@Test
	@DisplayName("다운로드 성공 테스트")
	void check_all_download_test_success() {
		CouponPromotion vo = new CouponPromotion();
		vo.setTotIssueCnt(1);
		vo.setMbrIssueCnt(1);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setDwlPsbCnt(2);
		cpn.setPsnDwlPsbCnt(2);
		
		vo.setCcCpnBase(cpn);
		
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		
		vo.setCcPrmBase(prm);
		Assertions.assertThat(vo.isDownloadable()).isEqualTo(false);
	}
	
	@Test
	@DisplayName("쿠폰 사용가능여부 테스트")
	void check_useable_success() {
		CouponPromotion vo = new CouponPromotion();
		CcCpnBase cpn = new CcCpnBase();
		cpn.setSysRegDtime(Timestamp.valueOf(LocalDateTime.now().plusDays(-1L)));
		
		vo.setCcCpnBase(cpn);
		
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now()));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
		
		vo.setCcPrmBase(prm);
		Assertions.assertThat(vo.isUseable()).isEqualTo(true);
	}
	
	@Test
	@DisplayName("쿠폰 복원 가능여부 테스트")
	void check_restore_fail() {
		CouponPromotion vo = new CouponPromotion();
		vo.setRestore(Constants.Y);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setSysRegDtime(Timestamp.valueOf(LocalDateTime.now().plusDays(-1L)));
		
		vo.setCcCpnBase(cpn);
		
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now()));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
		
		vo.setCcPrmBase(prm);
		Assertions.assertThat(vo.isRestoreable()).isEqualTo(false);
	}
	
	@Test
	@DisplayName("쿠폰 복원 가능여부 테스트")
	void check_restore_success() {
		CouponPromotion vo = new CouponPromotion();
		vo.setRestore(Constants.N);
		
		CcCpnBase cpn = new CcCpnBase();
		cpn.setSysRegDtime(Timestamp.valueOf(LocalDateTime.now().plusDays(-1L)));
		
		vo.setCcCpnBase(cpn);
		
		CcPrmBase prm = new CcPrmBase();
		prm.setUseYn(Constants.Y);
		prm.setPrmPriodCcd(PrmPriodCcd.TERM.getCode());
		prm.setPrmStrtDt(Timestamp.valueOf(LocalDateTime.now()));
		prm.setPrmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(3L)));
		
		vo.setCcPrmBase(prm);
		Assertions.assertThat(vo.isRestoreable()).isEqualTo(true);
	}
	
	@Test
	void groupingDistinct() {
		List<PromotionAply> promoList = Arrays.asList(
				PromotionAply.builder().prmNo(1L).prmNm("프로모션1").ordBnfAmt(3000L).build(),
				PromotionAply.builder().prmNo(1L).prmNm("프로모션1").ordBnfAmt(3000L).build(), 
				PromotionAply.builder().prmNo(2L).prmNm("프로모션2").ordBnfAmt(5000L).build() 
				);
		
		Map<Long, List<PromotionAply>> prmMap = promoList.stream()
			.collect(Collectors.groupingByConcurrent(PromotionAply::getPrmNo))
			;
		
		
		for(Entry<Long, List<PromotionAply>> map : prmMap.entrySet()) {
			log.info("key is {}", map.getKey());
			map.getValue().forEach(p -> {
				log.info("promotion aply is {}", p.toString());
			});
		}
		
		
//		List<PromotionAply> distinctList = promoList.stream().distinct()
//			.collect(Collectors.toList());
//		
//		for(PromotionAply promoAply : distinctList) {
//			log.info("distinct promotion aply is {}", promoAply);
//		}
		
		List<PromotionAply> distinctList  = prmMap.entrySet()
				.stream()
				.map(m -> m.getValue().get(0))
				.collect(Collectors.toList());
		for(PromotionAply promoAply : distinctList) {
			log.info("distinct promotion aply is {}", promoAply);
		}
		
		
	}
	
}
