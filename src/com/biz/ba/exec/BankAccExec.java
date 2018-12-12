package com.biz.ba.exec;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.biz.ba.service.BankAccService;

public class BankAccExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strAcc = "src/com/biz/ba/vo/bankBalance.txt";
		BankAccService BAS = new BankAccService(strAcc);
		
		
		BAS.readAccList();
		
		BAS.InputS();
		
		
		
	}

}
