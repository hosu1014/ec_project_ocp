package com.plateer.ec1.promotion.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.plateer.ec1.order.vo.OrderGoods;
import com.plateer.ec1.promotion.vo.ProductCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest

public class PromotionServiceTest {
	@Autowired
	private PromotionService promotionService; 
	
	
	@Test
	void productCouponAplyTest() {
		PromotionAplyReq req = new PromotionAplyReq();
		req.setMbrNo("test01");
		
		List<OrderGoods> orderGoodsList = new ArrayList();
		OrderGoods og1 = OrderGoods.builder()
				.goodsNo("P001")
				.itemNo("1")
				.cateNo("C0001")
				.entrNo("10001")
				.ordQty(1)
				.salePrc(3000L)
				.build();
		
		orderGoodsList.add(og1);
		
		OrderGoods og2 = OrderGoods.builder()
				.goodsNo("P002")
				.itemNo("1")
				.cateNo("C0001")
				.entrNo("10001")
				.ordQty(1)
				.salePrc(3000L)
				.build();
		
		orderGoodsList.add(og2);
		req.setOrderProductList(orderGoodsList);
		
		ProductCouponRes res =  promotionService.getProductCouponApplyData(req);
		log.info("coupon aply is {}", res);

		
	}
	
}
