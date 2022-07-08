package com.plateer.ec1.promotion.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.service.PromotionService;
import com.plateer.ec1.promotion.vo.CartCouponRes;
import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.ProductCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {
	private final CouponService couponService;
	private final PromotionService promotionService;
	
	@PostMapping("/couponDownload")
	public void couponDownload(@RequestBody CouponDownloadReq req) {
		couponService.couponDownload(req);
	}
	
	@PostMapping("/getProductBnf")
	public ProductCouponRes productCouponBnf(@Valid @RequestBody PromotionAplyReq req) {
		return promotionService.getProductCouponApplyData(req);
	}
	
	@PostMapping("/getCartCouponBnf")
	public CartCouponRes cartCouponBnf(@Valid @RequestBody PromotionAplyReq req) {
		return promotionService.getCartCouponApplyData(req);
	}
	
	@PostMapping("/getMaxBnfCoupon")
	public CartCouponRes maxBnfCouponAply(@Valid @RequestBody PromotionAplyReq req) {
		return promotionService.getMaxBnfCouponApplyData(req);
	}
	
	
}
