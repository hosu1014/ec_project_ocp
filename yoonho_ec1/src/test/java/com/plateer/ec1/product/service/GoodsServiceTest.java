package com.plateer.ec1.product.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.plateer.ec1.product.vo.Goods;
import com.plateer.ec1.product.vo.GoodsReq;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class GoodsServiceTest {
	@Autowired
	private GoodsService goodsService;
	
	@Test
	void getGoodsTest() {
		
		List<Goods> reqList = new ArrayList<>();
		reqList.add(Goods.builder().goodsNo("P001").itemNo("1").build());
		reqList.add(Goods.builder().goodsNo("P002").itemNo("1").build());
		
		GoodsReq req = new GoodsReq();
		req.setReqList(reqList);
		
//		for(Goods goods : goodsService.getGoodsList(req)) {
//			log.info("goods info is {}", goods);
//		}
		Assertions.assertThat(goodsService.getGoodsList(req))
			.isNotEmpty()
			.hasSize(2);
		
	}
}
