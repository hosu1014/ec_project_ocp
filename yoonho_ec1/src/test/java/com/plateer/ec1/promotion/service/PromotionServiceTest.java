package com.plateer.ec1.promotion.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.plateer.ec1.order.vo.OrderGoods;
import com.plateer.ec1.promotion.DaoMockTestConfig;
import com.plateer.ec1.promotion.vo.ProductCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;
import com.plateer.ec1.util.Constants;
import com.plateer.ec1.util.JsonFileReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Import(DaoMockTestConfig.class) // Dao를 목 처리하기 위해 환경을 별도 설정 함.
public class PromotionServiceTest {
	@Autowired
	private PromotionService promotionService; 
	
	
	@Test
	@DisplayName("P001-1번 상품 최대할인 적용 1000원")
	void productCouponAplyTest() {
		PromotionAplyReq req = JsonFileReader.getObject("promotion/service/product_coupon_req.json", PromotionAplyReq.class);
		
		ProductCouponRes res =  promotionService.getProductCouponApplyData(req);
		
		OrderGoods op_P0011 = res.getOrderProductList().stream()
			.filter(og -> og.getKey().equals("P0011"))
			.findFirst()
			.orElse(null);
		
		Assertions.assertThat(op_P0011.getAplyPromotion().getOrdBnfAmt())
		.isEqualTo(1000);
		Assertions.assertThat(op_P0011.getAplyPromotion().getCpnIssNo())
		.isEqualTo(11);
	}
	
	@Test
	@DisplayName("P006-1번 상품 최대할인 적용 15000원")
	void productCouponAplyTest_P006() {
		PromotionAplyReq req = JsonFileReader.getObject("promotion/service/product_coupon_req.json", PromotionAplyReq.class);
		
		ProductCouponRes res =  promotionService.getProductCouponApplyData(req);
		
		OrderGoods op_P0011 = res.getOrderProductList().stream()
			.filter(og -> og.getKey().equals("P0061"))
			.findFirst()
			.orElse(null);
		
		Assertions.assertThat(op_P0011.getAplyPromotion().getOrdBnfAmt())
		.isEqualTo(15000);
		Assertions.assertThat(op_P0011.getAplyPromotion().getCpnIssNo())
		.isEqualTo(15);
	}
	
	@Test
	void fullyTest() {
		PromotionAplyReq req = JsonFileReader.getObject("promotion/service/product_coupon_req.json", PromotionAplyReq.class);
		ProductCouponRes res =  promotionService.getProductCouponApplyData(req);
		
		for(OrderGoods og : res.getOrderProductList()) {
			resValueTest(og);
		}
		
	}

	@Test
	void resValueTest(OrderGoods og) {
		if(og.getPromotionAplyList()==null) return;
		long maxBnfPromotionCnt = og.getPromotionAplyList().stream()
									.filter(pa -> Constants.Y.equals( pa.getMaxBnfYn()))
									.count();
		Assertions.assertThat(maxBnfPromotionCnt)
			.as("최대혜택 적용 설정건수는 1보다 작거나 같아야 함.")
			.isLessThanOrEqualTo(1);
		
		long maxBnfPromotionCntWithAnotherAply = og.getPromotionAplyList().stream()
				.filter(pa -> Constants.Y.equals( pa.getMaxBnfYn()) && Constants.Y.equals(pa.getAnotherGoodsAplyYn()) )
				.count();
		Assertions.assertThat(maxBnfPromotionCntWithAnotherAply)
			.as("다른상품에 적용된 쿠폰은 최대혜택으로 설정되지 않아야 함.")
			.isEqualTo(0);
		
		// 기적용 여부가 Y이면 최대혜택으로 설정 되어야 한다. 
		long cpnAplyYisMaxBnfY = og.getPromotionAplyList().stream()
				.filter(pa -> Constants.Y.equals( pa.getCpnAplyYn()) && Constants.N.equals(pa.getMaxBnfYn()) )
				.count();
		Assertions.assertThat(cpnAplyYisMaxBnfY)
			.as("기적용 여부가 Y인데 최대혜택이 N인건 있으면 안된다. ")
			.isEqualTo(0);		
	}
	
	
}
