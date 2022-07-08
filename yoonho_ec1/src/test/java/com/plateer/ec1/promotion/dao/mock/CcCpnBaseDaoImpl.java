package com.plateer.ec1.promotion.dao.mock;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plateer.ec1.order.vo.OrderGoods;
import com.plateer.ec1.promotion.dao.CcCpnBaseDao;
import com.plateer.ec1.promotion.model.CcCpnBase;
import com.plateer.ec1.promotion.model.CcPrmBase;
import com.plateer.ec1.promotion.vo.CartCouponAply;
import com.plateer.ec1.promotion.vo.CouponDownloadReq;
import com.plateer.ec1.promotion.vo.CouponPromotion;
import com.plateer.ec1.promotion.vo.PromotionAplyReq;
import com.plateer.ec1.util.Constants;

public class CcCpnBaseDaoImpl implements CcCpnBaseDao {
	private final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
	private final Map<Long, CcCpnBase> cpnMap = new HashMap<>();
	private final Map<Long, CcPrmBase> prmMap = new HashMap<>();
	private final Map<String, CouponPromotion> couponPromotionMap = new HashMap<>();
	
	public CcCpnBaseDaoImpl() {
		makeDummyPrmData();
		makeDummyCpnData();
		makeDummyPromotionData();
	}
	
	private void makeDummyPromotionData() {
		long prmNo;
		prmNo = 1L;
		CouponPromotion download_true = CouponPromotion.builder()
				.prmNo(prmNo)
				.mbrNo("test01")
				.mbrIssueCnt(1)
				.totIssueCnt(2)
				.restore(Constants.Y)
				.ccCpnBase(cpnMap.get(prmNo))
				.ccPrmBase(prmMap.get(prmNo))
				.build();
		couponPromotionMap.put(download_true.getKey(), download_true);
		
		CouponPromotion download_false = CouponPromotion.builder()
				.prmNo(prmNo)
				.mbrNo("test02")
				.mbrIssueCnt(1)
				.totIssueCnt(2)
				.restore(Constants.Y)
				.ccCpnBase(cpnMap.get(prmNo))
				.ccPrmBase(prmMap.get(prmNo))
				.build();
		couponPromotionMap.put(download_false.getKey(), download_false);
		
		
		prmNo = 2L; // 다운로드 시작일 미도래 건 
		CouponPromotion cp2 = CouponPromotion.builder()
				.prmNo(prmNo)
				.mbrNo("test01")
				.mbrIssueCnt(1)
				.totIssueCnt(2)
				.restore(Constants.Y)
				.ccCpnBase(cpnMap.get(prmNo))
				.ccPrmBase(prmMap.get(prmNo))
				.build();
		couponPromotionMap.put(cp2.getKey(), cp2);
		
	}

	private void makeDummyCpnData() {
		CcCpnBase cpn1 = CcCpnBase.builder()
					.prmNo(1L)
					.dwlAvlStrtDd(LocalDateTime.now().plusDays(-1L).format(df))
					.dwlAvlEndDd(LocalDateTime.now().plusDays(2L).format(df))
					.dwlPsbCnt(10)
					.psnDwlPsbCnt(3)
					.build();
		cpnMap.put(cpn1.getPrmNo(), cpn1);
		
		CcCpnBase cpn2 = CcCpnBase.builder()
				.prmNo(2L)
				.dwlAvlStrtDd(LocalDateTime.now().plusDays(1L).format(df))
				.dwlAvlEndDd(LocalDateTime.now().plusDays(2L).format(df))
				.dwlPsbCnt(10)
				.psnDwlPsbCnt(3)
				.build();
		cpnMap.put(cpn2.getPrmNo(), cpn2);
	}

	private void makeDummyPrmData() {
		CcPrmBase prmNo1 = CcPrmBase.builder()
					.prmNo(1L)
					.dcCcd("10")
					.dcVal(1000L)
					.minPurAmt(5000)
					.prmPriodCcd("10")
					.prmStrtDt(Timestamp.valueOf(LocalDateTime.now().plusDays(-1L)))
					.prmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(2L)))
					.useYn(Constants.Y)
					.build();
		prmMap.put(prmNo1.getPrmNo(), prmNo1);
		
		CcPrmBase prmNo2 = CcPrmBase.builder()
				.prmNo(2L)
				.dcCcd("10")
				.dcVal(1000L)
				.minPurAmt(5000)
				.prmPriodCcd("10")
				.prmStrtDt(Timestamp.valueOf(LocalDateTime.now().plusDays(1L)))
				.prmEndDt(Timestamp.valueOf(LocalDateTime.now().plusDays(2L)))
				.useYn(Constants.Y)
				.build();
		prmMap.put(prmNo2.getPrmNo(), prmNo2);
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
