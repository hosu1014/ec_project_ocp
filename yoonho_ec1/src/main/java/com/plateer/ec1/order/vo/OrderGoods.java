package com.plateer.ec1.order.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plateer.ec1.promotion.vo.PromotionAply;
import com.plateer.ec1.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder @ToString @NoArgsConstructor @AllArgsConstructor
public class OrderGoods implements Cloneable {
	@NotEmpty
	private String goodsNo;
	@NotEmpty
	private String itemNo;
	private String goodsTpCd;
	@NotNull
	@Min(0)
	private Long salePrc;
	private String entrNo; 
	private String cateNo;
	private int ordQty;
	private Long cpnIssNo;
	private List<PromotionAply> promotionAplyList;
	
	@JsonIgnore
	public String getKey() {
		return this.getGoodsNo() + this.getItemNo();
	}
	
	@JsonIgnore
	public Long getCpnAplySalePrc() {
		PromotionAply pro = this.getAplyPromotion();
		if(pro == null) return this.getSalePrc();
		
		return this.getSalePrc() - pro.getOrdBnfAmt();
	}
	
	@JsonIgnore
	public PromotionAply getAplyPromotion() {
		if(this.getPromotionAplyList() == null) return null;
		
		return this.getPromotionAplyList()
				.stream()
				.filter(p -> Constants.Y.equals(p.getMaxBnfYn()))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public OrderGoods clone() {
		try {
			return (OrderGoods)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("clone exception");
		}
	}
}
