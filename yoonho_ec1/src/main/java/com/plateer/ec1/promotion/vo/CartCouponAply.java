package com.plateer.ec1.promotion.vo;

import java.util.List;

import com.plateer.ec1.order.vo.OrderGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CartCouponAply {
	private Long prmNo;
	private String degrCcd;
	private String cpnKindCd;
	private Long cpnIssNo;
	private Long ordBnfAmt;
	private String maxBnfYn;
	private List<OrderGoods> orderGoodsList;
}
