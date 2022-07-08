package com.plateer.ec1.promotion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.plateer.ec1.promotion.dao.CcCpnBaseDao;
import com.plateer.ec1.promotion.dao.CcCpnIssueTrxDao;
import com.plateer.ec1.promotion.dao.mock.CcCpnBaseDaoImpl;
import com.plateer.ec1.promotion.dao.mock.CcCpnIssueTrxDaoImpl;

@Configuration
public class DaoMockTestConfig {
	
	@Bean
	@Primary
	public CcCpnBaseDao cpnBaseDao() {
		return new CcCpnBaseDaoImpl();
	}
	
	@Bean
	@Primary
	public CcCpnIssueTrxDao cpnIssueTrxDao() {
		return new CcCpnIssueTrxDaoImpl();
	}
}
