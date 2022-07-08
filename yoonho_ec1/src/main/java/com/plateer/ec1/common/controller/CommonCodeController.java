package com.plateer.ec1.common.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plateer.ec1.base.LogTrace.annotation.LogTrace;
import com.plateer.ec1.common.service.StCmnCdServiceImpl;
import com.plateer.ec1.common.vo.StCmnCd;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommonCodeController {
	private final StCmnCdServiceImpl commonCodeService;
	
	@GetMapping("/code/getCode")
	@LogTrace
	public List<StCmnCd> getCodeList(@RequestParam(value = "cmnGrpCd", required = true) String cmnGrpCd) {
		log.debug("parameter is {}", cmnGrpCd);
		return commonCodeService.getCommonCodeList(cmnGrpCd);
	}
}
