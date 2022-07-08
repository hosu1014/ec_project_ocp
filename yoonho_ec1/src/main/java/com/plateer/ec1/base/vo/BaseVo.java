package com.plateer.ec1.base.vo;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.plateer.ec1.base.validator.OnCreate;
import com.plateer.ec1.base.validator.OnUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseVo {
	private Timestamp sysRegDtime;
	@NotNull(groups=OnCreate.class)
	private String sysRegrId;
	private Timestamp sysModDtime;
	@NotNull(groups= {OnCreate.class, OnUpdate.class})
	private String sysModrId;
}
