package com.plateer.ec1.promotion.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.validation.annotation.Validated;

import com.plateer.ec1.base.validator.OnCreate;
import com.plateer.ec1.base.validator.OnUpdate;
import com.plateer.ec1.promotion.model.CcCpnIssue;

@Mapper
@Validated
public interface CcCpnIssueTrxDao {
	
	@Validated(OnCreate.class)
	public void insertCcCpnIssue(@Valid CcCpnIssue cpnIssue);
	
	@Validated(OnUpdate.class)
	public int updateCouponUse(@Valid CcCpnIssue cpnIssue);
}
