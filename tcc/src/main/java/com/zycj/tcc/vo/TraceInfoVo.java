package com.zycj.tcc.vo;

public class TraceInfoVo {

	private String traceNo;
	private String referenceNo;
	private String cardNo;
	
	public TraceInfoVo(String traceNo,String referenceNo,String cardNo){
		this.traceNo = traceNo;
		this.referenceNo = referenceNo;
		this.cardNo = cardNo;
	}
	
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	
}
