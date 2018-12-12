package com.biz.ba.exec;

import java.util.Scanner;

import com.biz.ba.service.BankAccService01;

public class BankExec01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strbB = "src/com/biz/ba/vo/bankBalance.txt";
		BankAccService01 bs = new BankAccService01(strbB);
		Scanner scan = new Scanner(System.in);
		
		while(true) {
		
			System.out.println("================================================================");
			System.out.println("광한루 은행 차세대 시스템 1.0");
			System.out.println("----------------------------------------------------------------");
			System.out.println("1.입금  2.출금  3.계좌조회  4.종료");
			System.out.println("----------------------------------------------------------------");
			System.out.print("업무를 선택하세요 >> ");
			String strSelect = scan.nextLine();
			int intSelect = Integer.valueOf(strSelect);
			if(intSelect == 4) {
				System.out.println("업무를 종료합니다.");
				break;
			}
			
			bs.readFile();
			
			if(intSelect == 1) bs.inAcc();
			
			if(intSelect == 2) bs.outAcc();
			
			if(intSelect == 3) bs.view();
			
			bs.writeFile();
		}
	}

}
