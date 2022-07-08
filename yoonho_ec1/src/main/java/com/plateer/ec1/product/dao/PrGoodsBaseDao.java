package com.plateer.ec1.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.plateer.ec1.product.vo.Goods;
import com.plateer.ec1.product.vo.GoodsReq;

@Mapper
public interface PrGoodsBaseDao {
	public List<Goods> getGoodsList(GoodsReq req);
}
