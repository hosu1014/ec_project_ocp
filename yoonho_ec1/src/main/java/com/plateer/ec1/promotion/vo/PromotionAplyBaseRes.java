package com.plateer.ec1.promotion.vo;

import java.util.List;

import com.plateer.ec1.order.vo.OrderGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PromotionAplyBaseRes {
	private String mbrNo;
	private List<OrderGoods> orderProductList;
}
