package com.biz.ba.vo;

public class BankAccVO {

	private String strNum; 
	private int intBalance;
	private String strLastDay;

	public String getStrNum() {
		return strNum;
	}
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	public int getIntBalance() {
		return intBalance;
	}
	public void setIntBalance(int intBalance) {
		this.intBalance = intBalance;
	}
	public String getStrLastDay() {
		return strLastDay;
	}
	public void setStrLastDay(String strLastDay) {
		this.strLastDay = strLastDay;
	}
	@Override
	public String toString() {
		return "BanKAccVO [strNum=" + strNum + ", intBalance=" + intBalance + ", strLastDay=" + strLastDay + "]";
	}
	
	
}
