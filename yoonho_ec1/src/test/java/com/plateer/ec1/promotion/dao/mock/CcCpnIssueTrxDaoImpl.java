package com.plateer.ec1.promotion.dao.mock;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.plateer.ec1.promotion.dao.CcCpnIssueTrxDao;
import com.plateer.ec1.promotion.model.CcCpnIssue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CcCpnIssueTrxDaoImpl implements CcCpnIssueTrxDao{
	private final Map<Long, CcCpnIssue> issueMap = new HashMap<>();
	
	public CcCpnIssueTrxDaoImpl() {
		CcCpnIssue usePossible = CcCpnIssue.builder()
				.cpnIssNo(1L)
				.mbrNo("test01")
				.prmNo(1L)
				.build();
		
		issueMap.put(usePossible.getCpnIssNo(), usePossible);
		
		CcCpnIssue useImpossibleIssueNo = CcCpnIssue.builder()
				.cpnIssNo(2L)
				.mbrNo("test01")
				.prmNo(1L)
				.cpnUseDt(Timestamp.valueOf(LocalDateTime.now().minusDays(2L)))
				.build();
		
		issueMap.put(useImpossibleIssueNo.getCpnIssNo(), useImpossibleIssueNo);
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
