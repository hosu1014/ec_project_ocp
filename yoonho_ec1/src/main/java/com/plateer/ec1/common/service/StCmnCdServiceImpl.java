package com.plateer.ec1.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plateer.ec1.base.LogTrace.annotation.LogTrace;
import com.plateer.ec1.common.dao.StCmnCdDao;
import com.plateer.ec1.common.vo.StCmnCd;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StCmnCdServiceImpl {
	private final StCmnCdDao stCmnCdDao;
	
	@LogTrace
	public List<StCmnCd> getCommonCodeList(String cmnGrpCd) {
		log.debug("parameter is {}", cmnGrpCd);
		List<StCmnCd> commonCodeList = stCmnCdDao.getCommonCodeList(cmnGrpCd);
		
		return commonCodeList;
	}
}
