package com.plateer.ec1.promotion.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.plateer.ec1.order.vo.OrderGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PromotionAplyReq implements Cloneable {
	@NotEmpty
	private String mbrNo;
	@Size(min=1)
	@Valid
	private List<OrderGoods> orderProductList;
	private int size;
	
	/**
	 * mapper에서 사용하기 위해 설정한다. 
	 * 상품쿠폰의 발급번호는 주문하고자 하는 상품수 보다 작거나 같아야 한다. 
	 * @return
	 */
	public int getSize() {
		this.size = this.getOrderProductList()==null ? 0 : orderProductList.size();
		return size;
	}
	
	@Override
	public PromotionAplyReq clone() {
		PromotionAplyReq req;
		try {
			req = (PromotionAplyReq)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("");
		} 
		
		List<OrderGoods> cloneOrderGoods = new ArrayList<>();
		for(OrderGoods og : this.getOrderProductList()) {
			cloneOrderGoods.add(og.clone());
		}
		req.setOrderProductList(cloneOrderGoods);
		
		return req;
	}
}
