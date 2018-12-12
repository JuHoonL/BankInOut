package com.biz.ba.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.biz.ba.vo.BankAccVO;

public class BankAccService01 {

	List<BankAccVO> bankList ;
	String strAcc;
	int intNum;
	
	public BankAccService01(String strAcc) {
		bankList = new ArrayList();
		this.strAcc = strAcc;
	}
	
	public void readFile() {
		FileReader fr ;
		BufferedReader br;
		
		try {
			fr = new FileReader(strAcc);
			br = new BufferedReader(fr);
			
			while(true) {
				String readL = br.readLine();
				if(readL == null) break;
				String[] splitRF = readL.split(":");
				BankAccVO vo = new BankAccVO();
				vo.setStrNum(splitRF[0]);
				vo.setIntBalance(Integer.valueOf(splitRF[1]));
				vo.setStrLastDay(splitRF[2]);
				bankList.add(vo);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void bankInPut() {
		Scanner scan = new Scanner(System.in);
			
		System.out.print("계좌번호를 입력하세요 >> ");
		String strAcc = scan.nextLine();
		BankAccVO vo = null;
		int Len = bankList.size();
		for(int i = 0 ; i < Len ; i ++) {
			if(strAcc.equals(bankList.get(i).getStrNum())) {
				vo = bankList.get(i);
				intNum = i;
			} 
		}
		if(vo == null) {
			System.out.println("계좌번호가 없다.");	
			return ;
		}
		
		
	}
			
		
	public void inAcc() {	
		Scanner scan = new Scanner(System.in);	
		BankAccVO vo = bankList.get(intNum);
		System.out.print("입금액 >> ");
		String strBal = scan.nextLine();
		int intinBal = vo.getIntBalance();
		intinBal += Integer.valueOf(strBal);
		vo.setIntBalance(intinBal);
/*		String strDate = LocalDate.now().toString();
		vo.setStrLastDay(strDate); */
		SimpleDateFormat sdF = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date d = new Date();
		String today = sdF.format(d);
		vo.setStrLastDay(today);
		System.out.println("================================================================");
		System.out.println("입금이 완료되었습니다.");
		System.out.println("================================================================");
		System.out.println("임금 전 잔액 : " + (vo.getIntBalance() - Integer.valueOf(strBal)));
		System.out.println("입금액 : " + strBal);
		System.out.println("현재 잔액 : " + vo.getIntBalance());
	}
	
	public void outAcc() {
		Scanner scan = new Scanner(System.in);
		BankAccVO vo = bankList.get(intNum);
		System.out.print("출금액 >> ");
		String strMBal = scan.nextLine();
		int intOutBal = vo.getIntBalance();
		intOutBal -= Integer.valueOf(strMBal);
		if(intOutBal < 0) {
			System.out.println("잔액이 부족합니다.");
			return;
		}
		vo.setIntBalance(intOutBal);
		SimpleDateFormat sdF = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date d = new Date();
		String today = sdF.format(d);
		vo.setStrLastDay(today);
		System.out.println("================================================================");
		System.out.println("출금이 완료되었습니다.");
		System.out.println("================================================================");
		System.out.println("출금 전 잔액 : " + (vo.getIntBalance() + Integer.valueOf(strMBal)));
		System.out.println("출금액 : " + strMBal);
		System.out.println("현재 잔액 : " + vo.getIntBalance());
	}
	
	public void view() {
		BankAccVO vo = bankList.get(intNum);
		System.out.println(vo);
		
	}
	
	public void writeFile() {
		FileWriter fw;
		PrintWriter pw;
		
		try {
			fw = new FileWriter(strAcc);
			pw = new PrintWriter(fw);
			
			for(BankAccVO v : bankList) {
				pw.print(v.getStrNum() + ":");
				pw.print(v.getIntBalance() + ":");
				pw.print(v.getStrLastDay() + "\n");
			}
			pw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
