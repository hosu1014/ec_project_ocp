package com.plateer.ec1.claim.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.enums.DeliveryType;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class ClaimProduct extends OrderProduct implements Cloneable {
	@Min(value=1, message="{claim.quantity.minvalue}")
	private int claimQty;
	private Long deliveryNo;
	@javax.validation.constraints.NotBlank(message="{NotBlank.Claim.deliveryGroup.productList.claimReasonTpCd}")
	private String claimReasonTpCd;
	private Benefit cnclBenefit;
	private Benefit aplyBenefit;
	
	public List<ClaimProduct> getInsertData(ClaimType claimType, int processSeq) {
		List<ClaimProduct> cpList = new ArrayList<>();
		
		List<DeliveryType> dtList = claimType.getDeliveryTypeList();		
		for(int i=0;i<dtList.size();i++) {
			ClaimProduct cp = clone();
			cp.setProcessSeq(processSeq + i);
			cp.setOrderClaimTpCd(claimType.getClaimType());
			cp.setOrderPrgsScd(claimType.getDesirePrgsStCd().getCode());
			cp.setOrdQty(this.getClaimQty());
			cp.setOrgProcessSeq(this.getProcessSeq());
			cp.setDlvRvtCcd(dtList.get(i).getCode());
			cp.setDirectDt(null);
			
			
			List<Benefit> benefitList = new ArrayList<Benefit>();
			this.getBenefit().forEach(b -> benefitList.add(b.getInsertData(claimType)));
			cp.setBenefit(benefitList);
			
			cpList.add(cp);
		}
		
		return cpList;
	}

	public List<ClaimProduct> getUpdateData(ClaimType claimType) {
		List<ClaimProduct> cpList = new ArrayList<>();
		ClaimProduct cp = clone();
		setUpdateQty(claimType, cp);
		cp.setBenefit(null);
		cpList.add(cp);
		
		if((claimType == ClaimType.EXCHANGE_CANCEL || claimType == ClaimType.RETURN_CANCEL) 
				&& this.getDlvRvtCcd() == DeliveryType.RETURN.getCode()) {
			ClaimProduct cp1 = clone();
			cp1.setProcessSeq(this.getOrgProcessSeq());
			cp1.setRtgsQty(-1 * this.getClaimQty());
			cp1.setBenefit(null);
			cpList.add(cp1);
		}
		
		
		return cpList;
	}
	
	@Override
	public ClaimProduct clone() {
		ClaimProduct cp;
		try {
			cp = (ClaimProduct)super.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("");
		}
		return cp;
	}
	
	

	private void setUpdateQty(ClaimType claimType, ClaimProduct cp) {
		switch (claimType) {
		case CANCEL:
		case RETURN_CANCEL:
		case EXCHANGE_CANCEL:
			cp.setCnclQty(this.getClaimQty());
			break;
		case RETURN:
		case EXCHANGE:
			cp.setRtgsQty(this.getClaimQty());
		default:
			break;
		}
	}
}