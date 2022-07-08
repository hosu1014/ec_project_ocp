package com.plateer.ec1.claim.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plateer.ec1.claim.vo.Claim;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/claim")
@Slf4j
@RequiredArgsConstructor
public class ClaimController {
	
	@PostMapping("/process")
	public String claimProcess(@RequestBody @Valid Claim claim) {
		
		return claim.toString();
	}
}
