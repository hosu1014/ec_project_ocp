package com.plateer.ec1.promotion.dao.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.plateer.ec1.order.vo.OrderGoods;
import com.plateer.ec1.promotion.dao.CcCpnBaseDao;
import com.plateer.ec1.promotion.model.CcCpnBase;
import com.plateer.ec1.promotion.model.CcPrmBase;
import com.plateer.ec1.promotion.vo.CartCouponAply;
import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.CouponPromotion;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;
import com.plateer.ec1.util.JsonFileReader;

public class CcCpnBaseDaoImpl implements CcCpnBaseDao {
	private final Map<Long, CcCpnBase> cpnMap = new HashMap<>();
	private final Map<Long, CcPrmBase> prmMap = new HashMap<>();
	private final Map<String, CouponPromotion> couponPromotionMap = new HashMap<>();
	
	public CcCpnBaseDaoImpl() {
		makeDummyPrmData();
		makeDummyCpnData();
		makeDummyPromotionData();
	}
	
	private void makeDummyPromotionData() {
		List<CouponPromotion> cpnBaseList = JsonFileReader.getObject("promotion/CouponPromotion.json", new TypeReference<List<CouponPromotion>>() {});
		cpnBaseList.stream()
			.forEach(cp -> {
				couponPromotionMap.put(cp.getKey(), cp);
			});
		
	}

	private void makeDummyCpnData() {
		List<CcCpnBase> cpnBaseList = JsonFileReader.getObject("promotion/CcCpnBase.json", new TypeReference<List<CcCpnBase>>() {});
		cpnBaseList.stream()
			.forEach(cpn -> {
				cpnMap.put(cpn.getPrmNo(), cpn);
			});
	}

	private void makeDummyPrmData() {
		List<CcPrmBase> cpnBaseList = JsonFileReader.getObject("promotion/CcPrmBase.json", new TypeReference<List<CcPrmBase>>() {});
		cpnBaseList.stream()
			.forEach(prm -> {
				prmMap.put(prm.getPrmNo(), prm);
			});
	}
	
	@Override
	public List<OrderGoods> getProductCouponAplyList(PromotionAplyReq req) {
		return null;
	}
	
	@Override
	public CouponPromotion getCouponBase(CouponDownloadReq req) {
		return couponPromotionMap.get(req.getKey());
	}
	
	@Override
	public List<CartCouponAply> getCartCouponAplyList(PromotionAplyReq req) {
		return null;
	}
}
