package com.plateer.ec1.claim.vo;

import lombok.Data;

@Data
public class Promotion implements Cloneable {
	private Long prmNo;
	private String cpnKndCd;
	private String degrCcd;
	private Long cpnIssNo;
	
	@Override
	public Promotion clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Promotion)super.clone();
	}
}
