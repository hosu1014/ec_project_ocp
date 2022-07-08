package com.plateer.ec1.promotion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.ec1.order.vo.OrderGoods;
import com.plateer.ec1.promotion.vo.CartCouponAply;
import com.plateer.ec1.promotion.vo.CouponAply;
import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.CouponPromotion;
import com.plateer.ec1.promotion.vo.PromotionAply;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;

@Mapper
public interface CcCpnBaseDao {
	public CouponPromotion getCouponBase(CouponDownloadReq coupon);
	public List<OrderGoods> getProductCouponAplyList(PromotionAplyReq req);
	public List<CartCouponAply> getCartCouponAplyList(PromotionAplyReq req);
}
