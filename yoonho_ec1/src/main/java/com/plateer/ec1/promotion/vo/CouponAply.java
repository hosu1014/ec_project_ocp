package com.plateer.ec1.promotion.vo;

import java.util.List;

import com.plateer.ec1.order.vo.OrderGoods;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CouponAply {
	private String mbrNo;
	private OrderGoods orderGoods;
	private List<Long> useCpnIssNo; 
}
