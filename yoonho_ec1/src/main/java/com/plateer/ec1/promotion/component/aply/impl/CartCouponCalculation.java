package com.plateer.ec1.promotion.component.aply.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.plateer.ec1.promotion.component.aply.Calculation;
import com.plateer.ec1.promotion.dao.CcCpnBaseDao;
import com.plateer.ec1.promotion.enums.CpnKindCd;
import com.plateer.ec1.promotion.vo.CartCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CartCouponCalculation implements Calculation<CartCouponRes> {
	private final CcCpnBaseDao cpnBaseDao;
	
	
	@Override
	public CpnKindCd getType() {
		return CpnKindCd.CART_CUP;
	}
	
	@Override
	public CartCouponRes getCalculationData(PromotionAplyReq req) {
		CartCouponRes res = new CartCouponRes();
		BeanUtils.copyProperties(req, res);
		
		res.setCartCouponAplyList(cpnBaseDao.getCartCouponAplyList(req));
		return res;
	}

	

	
}
