package com.plateer.ec1.promotion.component.aply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.plateer.ec1.promotion.enums.CpnKindCd;
import com.plateer.ec1.promotion.vo.PromotionAplyBaseRes;

@Component
public class CalculationFactory {
	
	private final Map<CpnKindCd, Calculation<? extends PromotionAplyBaseRes>> map = 
			new HashMap<CpnKindCd, Calculation<? extends PromotionAplyBaseRes>>();
	
	public CalculationFactory(List<Calculation<? extends PromotionAplyBaseRes>> cals) {
		cals.forEach(c -> map.put(c.getType(), c));
	}
		
	public Calculation<? extends PromotionAplyBaseRes> getPromotionCalculation(CpnKindCd type) {
		return map.get(type);
	}
}

