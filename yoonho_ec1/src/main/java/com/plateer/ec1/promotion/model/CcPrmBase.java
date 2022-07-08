package com.plateer.ec1.promotion.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import com.plateer.ec1.base.vo.BaseVo;
import com.plateer.ec1.promotion.PrmPriodCcd;
import com.plateer.ec1.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class CcPrmBase extends BaseVo {
	private Long prmNo;
	private String prmNm;
	private String prmKindCd;
	private String prmPriodCcd;
	private Timestamp prmStrtDt;
	private Timestamp prmEndDt;
	private int prmStdDd;
	private String empYn;
	private String dcCcd;
	private double dcVal;
	private int minPurAmt;
	private int maxDcAmt;
	private String useYn;
	private String rmk;
	
	public boolean isUseable(Timestamp regDtime) {
		if(!Constants.Y.equals(this.getUseYn())) return false;
		Timestamp today = Timestamp.valueOf(LocalDateTime.now());
		
		if(PrmPriodCcd.of(this.getPrmPriodCcd()) == PrmPriodCcd.TERM) {		
			return (today.after(this.getPrmStrtDt()) || today.equals(this.getPrmStrtDt()))
					&& (today.before(this.getPrmEndDt()) || today.equals(this.getPrmEndDt()));
		} else {
			if(regDtime == null) return false;
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(regDtime.getTime());
			cal.add(Calendar.DATE, this.getPrmStdDd());
			
			return today.before(new Timestamp(cal.getTime().getTime()));
		}
				
	}
}
