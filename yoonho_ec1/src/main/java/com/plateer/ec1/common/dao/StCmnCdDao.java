package com.plateer.ec1.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.ec1.base.LogTrace.annotation.LogTrace;
import com.plateer.ec1.common.vo.StCmnCd;

@Mapper
public interface StCmnCdDao {
	
	@LogTrace
	public List<StCmnCd> getCommonCodeList(String cmnGrpCd);
}
