package com.plateer.ec1.promotion.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CartCouponRes extends PromotionAplyBaseRes {
	List<CartCouponAply> cartCouponAplyList;
}
