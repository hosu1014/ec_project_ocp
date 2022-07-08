package com.plateer.ec1.promotion.component.aply.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.plateer.ec1.order.vo.OrderGoods;
import com.plateer.ec1.promotion.component.aply.Calculation;
import com.plateer.ec1.promotion.dao.CcCpnBaseDao;
import com.plateer.ec1.promotion.enums.CpnKindCd;
import com.plateer.ec1.promotion.vo.ProductCouponRes;
import com.plateer.ec1.promotion.vo.PromotionAply;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;
import com.plateer.ec1.util.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCouponCalculation implements Calculation<ProductCouponRes>{
	private final CcCpnBaseDao cpnBaseDao;
	
	@Override
	public CpnKindCd getType() {
		return CpnKindCd.PRD_CUP;
	}

	@Override
	public ProductCouponRes getCalculationData(PromotionAplyReq req) {
		List<OrderGoods> productCouponAplyList = cpnBaseDao.getProductCouponAplyList(req);
		
		// 타 상품적용 쿠폰 발급번호 관리용 - 제일 마지막에 타 상품 적용여부 설정할 때 사용된다. 
		List<Long> anotherGoodsAplyCpnIssNos = new ArrayList<>();
		for(int i=0; i<productCouponAplyList.size();i++) {
			Long cpnIssNo = productCouponAplyList.get(i)
					.getPromotionAplyList()
					.stream()
					.filter(p -> Constants.N.equals(p.getAnotherGoodsAplyYn()))
					.findFirst()
					.map((promotion) -> {
						promotion.setMaxBnfYn(Constants.Y);
						return promotion.getCpnIssNo();
					})
					.orElse(null);
			
			
			if(cpnIssNo != null) {
				anotherGoodsAplyCpnIssNos.add(cpnIssNo);
				// 다른 상품 적용여부를 설정 
				setPromotionAnotherPrm(i, cpnIssNo, productCouponAplyList);
			}
		}
		
		setAnotherGoodsAplyYn(anotherGoodsAplyCpnIssNos, productCouponAplyList);

		return setPrmAplyInfoToReqestProductList(req, productCouponAplyList);
	}

	/**
	 * 프로모션 적용 정보를 요청상품 리스트에 설정하고, 응답값에 설정한다. 
	 * @param req
	 */
	private ProductCouponRes setPrmAplyInfoToReqestProductList(PromotionAplyReq req, List<OrderGoods> productCouponAplyList) {
		Map<String, List<PromotionAply>> promotionAplyMap = new HashMap<String, List<PromotionAply>>();
		productCouponAplyList.stream()
			.forEach(og -> {
				promotionAplyMap.put(og.getKey(), og.getPromotionAplyList());
			});
				
				
				
		req.getOrderProductList().stream()
			.forEach(og -> og.setPromotionAplyList(promotionAplyMap.get(og.getKey())));

		ProductCouponRes res = new ProductCouponRes();
		res.setMbrNo(req.getMbrNo());
		res.setOrderProductList(req.getOrderProductList());
		
		return res;
	}

	/**
	 * 쿠폰발급번호가 다른 상품에 적용되었는지 설정한다.  
	 * @param anotherGoodsAplyCpnIssNos	다른상품에 적용된 쿠폰발급번호 리스트 
	 * @param orderProductSortedList
	 */
	private void setAnotherGoodsAplyYn(List<Long> anotherGoodsAplyCpnIssNos, List<OrderGoods> orderProductSortedList) {
		orderProductSortedList
			.stream()
			.flatMap(og -> og.getPromotionAplyList().stream())
			.forEach(pa -> {
				if(anotherGoodsAplyCpnIssNos.contains(pa.getCpnIssNo())
						&& !Constants.Y.equals(pa.getMaxBnfYn())
						&& !Constants.Y.equals(pa.getCpnAplyYn())) {
					pa.setAnotherGoodsAplyYn(Constants.Y);
				}	
			});
	}

	/**
	 * index번호 이후의 적용상품리스트에서 쿠폰발급번호가 다른 상품에 적용되었는지 설정한다. 
	 * @param index
	 * @param cpnIssNo
	 * @param orderProductList
	 */
	private void setPromotionAnotherPrm(int index, Long cpnIssNo, List<OrderGoods> orderProductList) {
		if(index+1 == orderProductList.size()) return;
		
		for (int i = index + 1; i < orderProductList.size(); i++) {
			List<PromotionAply> promotionList = orderProductList.get(i).getPromotionAplyList();
			if (promotionList == null || promotionList.size() == 0)
				return;

			Map<Long, PromotionAply> promotionMap = 
					orderProductList.get(i)
									.getPromotionAplyList()
									.stream()
									.collect(Collectors.toMap(PromotionAply::getCpnIssNo, Function.identity()));

			if (promotionMap.containsKey(cpnIssNo)) {
				promotionMap.get(cpnIssNo).setAnotherGoodsAplyYn(Constants.Y);
			}
		}
	}
}
