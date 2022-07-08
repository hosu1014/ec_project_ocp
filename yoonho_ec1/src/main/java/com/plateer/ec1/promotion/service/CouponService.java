package com.plateer.ec1.promotion.service;

import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.plateer.ec1.base.audit.annotation.SetLoginUser;
import com.plateer.ec1.config.exception.BusinessException;
import com.plateer.ec1.promotion.dao.CcCpnBaseDao;
import com.plateer.ec1.promotion.dao.CcCpnIssueTrxDao;
import com.plateer.ec1.promotion.model.CcCpnIssue;
import com.plateer.ec1.promotion.validator.CouponRestore;
import com.plateer.ec1.promotion.validator.CouponUse;
import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.CouponPromotion;
import com.plateer.ec1.promotion.vo.CouponUseReq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class CouponService {
	private final CcCpnBaseDao cpnBaseDao;
	private final CcCpnIssueTrxDao cpnIssueTrxDao;
	
	@SetLoginUser
	@Validated
	public void couponDownload(@Valid CouponDownloadReq req) {
		if(!isCouponDownloadable(req)) throw new BusinessException("promotion.coupon.download.notavailable");
		
		CcCpnIssue cpnIssue = new CcCpnIssue();
		BeanUtils.copyProperties(req, cpnIssue);
		cpnIssueTrxDao.insertCcCpnIssue(cpnIssue);
	}
	
	@SetLoginUser
	@Validated({CouponUse.class, Default.class})
	public void couponUse(@Valid CouponUseReq req) {
		if(!isCouponUseable(req)) throw new BusinessException("promotion.coupon.use.imposible");
		
		CcCpnIssue cpnIssue = new CcCpnIssue();
		BeanUtils.copyProperties(req, cpnIssue);
		if(cpnIssueTrxDao.updateCouponUse(cpnIssue) == 0) throw new BusinessException("promotion.coupon.use.imposible.issueno");
	}
	
	@SetLoginUser
	@Validated({CouponRestore.class, Default.class})
	public void couponRestore(@Valid CouponUseReq req) {
		if(!isRestoreable(req)) return;
		
		CcCpnIssue cpnIssue = new CcCpnIssue();
		BeanUtils.copyProperties(req, cpnIssue);
		cpnIssue.setOrgCpnIssNo(req.getCpnIssNo());
		cpnIssueTrxDao.insertCcCpnIssue(cpnIssue);
	}
	
	public boolean isCouponDownloadable(CouponDownloadReq req) {
		CouponPromotion cpnPromotion = cpnBaseDao.getCouponBase(req);
		return cpnPromotion.isDownloadable();
	}

	public boolean isRestoreable(CouponUseReq req) {
		CouponPromotion cpnPromotion = getCouponPromotionInfo(req);
		return cpnPromotion.isRestoreable();
	}

	public boolean isCouponUseable(CouponUseReq req) {
		CouponPromotion cpnPromotion = getCouponPromotionInfo(req);
		
		return cpnPromotion.isUseable();
	}

	private CouponPromotion getCouponPromotionInfo(CouponUseReq req) {
		CouponDownloadReq couponInquiryVo = new CouponDownloadReq();
		BeanUtils.copyProperties(req, couponInquiryVo);
		CouponPromotion cpnPromotion = cpnBaseDao.getCouponBase(couponInquiryVo);
		return cpnPromotion;
	}
	
	
	
}
