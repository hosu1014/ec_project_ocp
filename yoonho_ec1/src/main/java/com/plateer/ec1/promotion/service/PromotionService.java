package com.plateer.ec1.promotion.service;

import org.springframework.stereotype.Service;

import com.plateer.ec1.promotion.component.aply.CalculationFactory;
import com.plateer.ec1.promotion.enums.CpnKindCd;
import com.plateer.ec1.promotion.vo.CartCouponRes;
import com.plateer.ec1.promotion.vo.ProductCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {
	private final CalculationFactory factory;
	
	/**
	 * 상품쿠폰 최대 헤택 적용
	 * @param req
	 * @return
	 */
	public ProductCouponRes getProductCouponApplyData(PromotionAplyReq req) {
		return (ProductCouponRes)factory.getPromotionCalculation(CpnKindCd.PRD_CUP).getCalculationData(req);
	}

	/**
	 * 장바구니 쿠폰 최대 혜택 적용
	 * @param req
	 * @return
	 */
	public CartCouponRes getCartCouponApplyData(PromotionAplyReq req) {
		return (CartCouponRes)factory.getPromotionCalculation(CpnKindCd.CART_CUP).getCalculationData(req);
	}
	
	/**
	 * 최대혜택 쿠폰 적용 데이터 조회 
	 * @param req
	 * @return
	 */
	public CartCouponRes getMaxBnfCouponApplyData(PromotionAplyReq req) {
		ProductCouponRes productCouponRes = getProductCouponApplyData(req);
		
		// 상품가격을 상품쿠폰 적용 가격으로 설정
		productCouponRes.getOrderProductList()
			.stream()
			.forEach(og -> og.setSalePrc(og.getCpnAplySalePrc()));
		
		req.setOrderProductList(productCouponRes.getOrderProductList());
		
		return (CartCouponRes)factory.getPromotionCalculation(CpnKindCd.CART_CUP).getCalculationData(req);
	}
}
