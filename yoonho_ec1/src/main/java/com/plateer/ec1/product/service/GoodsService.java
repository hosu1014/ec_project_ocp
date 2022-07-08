package com.plateer.ec1.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plateer.ec1.product.dao.PrGoodsBaseDao;
import com.plateer.ec1.product.vo.Goods;
import com.plateer.ec1.product.vo.GoodsReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoodsService {
	private final PrGoodsBaseDao prGoodsBase;
	
	public List<Goods> getGoodsList(GoodsReq req) {
		return prGoodsBase.getGoodsList(req);
	}
}
