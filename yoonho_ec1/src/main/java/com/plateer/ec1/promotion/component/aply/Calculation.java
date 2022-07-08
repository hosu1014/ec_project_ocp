package com.plateer.ec1.promotion.component.aply;

import com.plateer.ec1.promotion.enums.CpnKindCd;
import com.plateer.ec1.promotion.vo.PromotionAplyBaseRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

public interface Calculation<T extends PromotionAplyBaseRes> {
	public CpnKindCd getType();
	public T getCalculationData(PromotionAplyReq reqVo);
}
