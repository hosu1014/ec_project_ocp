package com.plateer.ec1.util;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plateer.ec1.promotion.model.CcCpnBase;
import com.plateer.ec1.promotion.vo.ProductCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonFileReaderTest {
	
	@Test
	public void PromotionAplyReq_read() {
		PromotionAplyReq req = JsonFileReader.getObject("promotion/promotion_aply_req.json", PromotionAplyReq.class);
		log.info("PromotionAplyReq is {}", req);
	}
	
	@Test
	public void ProductCouponRes_read() {
		ProductCouponRes res = JsonFileReader.getObject("promotion/product_coupon_res.json", ProductCouponRes.class);
		log.info("PromotionAplyReq is {}", res);
		
	}
	
	@Test
	public void list_read() {
		List<CcCpnBase> res = JsonFileReader.getObject("promotion/CcCpnBase.json", new TypeReference<List<CcCpnBase>>() {});
		log.info("PromotionAplyReq is {}", res);
		log.info("PromotionAplyReq is {}", res.get(0).getPrmNo());
	}
	
}
