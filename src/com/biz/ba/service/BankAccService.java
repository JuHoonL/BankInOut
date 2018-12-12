package com.biz.ba.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.biz.ba.vo.BankAccVO;

public class BankAccService {

	List<BankAccVO> bankList ;
	String strAcc;
	
	public BankAccService(String strAcc) {
		bankList = new ArrayList();
		this.strAcc = strAcc;
	}
	
	public void readAccList() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(strAcc);
			br = new BufferedReader(fr);
			
			while(true) {
				String readF = br.readLine();
				if(readF == null) break;
				String[] splitF = readF.split(":");
				BankAccVO vo = new BankAccVO();
				vo.setStrNum(splitF[0]);
				vo.setIntBalance(Integer.valueOf(splitF[1]));
				vo.setStrLastDay(splitF[2]);
				
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
	
	public void InputS() {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			SimpleDateFormat sdF = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA );
			Date CurrentTime = new Date();
			String toDay = sdF.format(CurrentTime);
			System.out.print("입금/출금/종료 >> ");
			String strPut = scan.nextLine();
			if(strPut.equals("종료")) break;
			System.out.print("계좌번호를 입력해 주십시요 >> ");
			String strAcc = scan.nextLine();
			Boolean b = false;
			for(BankAccVO sAvo : bankList) {
				if(strAcc.equals(sAvo.getStrNum())) {
					b = true;
				}
			}
			if(b == false) {
				System.out.println("계좌번호가 없습니다.");
				continue;
			}
		
			if(strPut.equals("입금")) {
				System.out.print("입금액을 입력해주십시요 >  ");
				String strIn = scan.nextLine();
				for(BankAccVO vi : bankList) {
					if(strAcc.equals(vi.getStrNum())) {
						vi.setIntBalance(vi.getIntBalance()+Integer.valueOf(strIn));
						vi.setStrLastDay(toDay);
						System.out.println("현재 계좌내 잔액은 " + vi.getIntBalance() + "입니다.");
					} 
				}
			}
			
			if(strPut.equals("출금")) {
				System.out.print("출금액을 입력해주십시요 > ");
				String strOut = scan.nextLine();
				int intOutLen = bankList.size();
				for(BankAccVO vo : bankList) {
					if(strAcc.equals(vo.getStrNum())) {
						int intMaBal = vo.getIntBalance()-Integer.valueOf(strOut);
						if(intMaBal >= 0) {
							vo.setIntBalance(intMaBal);
							System.out.println("현재 계좌내 잔액은 " + vo.getIntBalance() + "입니다.");
							vo.setStrLastDay(toDay);
						}
						if(intMaBal < 0) {
							System.out.println("잔액이 부족합니다. 현재 잔액은 " + vo.getIntBalance() + "입니다.");
							System.out.println("잔액을 확인하고 다시 시작해주세요");
							break;
						} 
						
					}
				}
			}
		}
	}
	
	
	
	
}
