package com.plateer.ec1.promotion.vo;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BiFunctionTest {
	
	@Test
	void test() {
		BiFunction<Long, Integer, Double> calcDiscount = (salePrc, dcRate) -> {
			return Math.ceil(salePrc * dcRate / 100);
		};
		
		
		log.info("30000의 5% 는 {}", calcDiscount.apply(30000L, 5));
	}
}
