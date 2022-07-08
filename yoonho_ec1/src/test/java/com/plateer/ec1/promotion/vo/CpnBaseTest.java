package com.plateer.ec1.promotion.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.plateer.ec1.promotion.model.CcCpnBase;

public class CpnBaseTest {
	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	@Test
	@DisplayName("시작일 동일할 경우 성공 ")
	void term_check_same_startdate() {
		CcCpnBase cpnBase = new CcCpnBase();
		cpnBase.setDwlAvlStrtDd(LocalDateTime.now().format(df));
		cpnBase.setDwlAvlEndDd(LocalDateTime.now().plusDays(1L).format(df));
				
		Assertions.assertThat(cpnBase.termCheck()).isEqualTo(true);
	}
	
	@Test
	@DisplayName("시작일 이후 일 경우 성공 ")
	void term_check_1day_over_startdate() {
		CcCpnBase cpnBase = new CcCpnBase();
		cpnBase.setDwlAvlStrtDd(LocalDateTime.now().plusDays(-1L).format(df));
		cpnBase.setDwlAvlEndDd(LocalDateTime.now().plusDays(2L).format(df));
				
		Assertions.assertThat(cpnBase.termCheck()).isEqualTo(true);
	}
	
	@Test
	@DisplayName("종료일 동일할 경우 성공 ")
	void term_check_same_enddate() {
		CcCpnBase cpnBase = new CcCpnBase();
		cpnBase.setDwlAvlStrtDd(LocalDateTime.now().format(df));
		cpnBase.setDwlAvlEndDd(LocalDateTime.now().format(df));
				
		Assertions.assertThat(cpnBase.termCheck()).isEqualTo(true);
	}
	
	@Test
	@DisplayName("종료일 이후 일 경우 성공 ")
	void term_check_1day_over_enddate_fail() {
		CcCpnBase cpnBase = new CcCpnBase();
		cpnBase.setDwlAvlStrtDd(LocalDateTime.now().format(df));
		cpnBase.setDwlAvlEndDd(LocalDateTime.now().plusDays(-1L).format(df));
				
		Assertions.assertThat(cpnBase.termCheck()).isEqualTo(false);
	}
}
