package com.plateer.ec1.promotion.dao.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plateer.ec1.promotion.dao.CcCpnIssueTrxDao;
import com.plateer.ec1.promotion.model.CcCpnIssue;
import com.plateer.ec1.util.JsonFileReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CcCpnIssueTrxDaoImpl implements CcCpnIssueTrxDao{
	private final Map<Long, CcCpnIssue> issueMap = new HashMap<>();
	
	public CcCpnIssueTrxDaoImpl() {
		List<CcCpnIssue> cpnBaseList = JsonFileReader.getObject("promotion/CcCpnIssue.json", new TypeReference<List<CcCpnIssue>>() {});
		cpnBaseList.stream()
			.forEach(issue -> {
				issueMap.put(issue.getCpnIssNo(), issue);
			});
	}
	
	
	@Override
	public int updateCouponUse(@Valid CcCpnIssue cpnIssue) {
		CcCpnIssue iss = issueMap.get(cpnIssue.getCpnIssNo());
		if(iss == null || iss.getCpnUseDt() != null) return 0;
		return 1;
	}
	
	@Override
	public void insertCcCpnIssue(@Valid CcCpnIssue cpnIssue) {
		log.info("저장 성공");
	}
}
