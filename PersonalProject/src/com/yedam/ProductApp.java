package com.yedam;

import java.util.List;
import java.util.Scanner;

public class ProductApp {
	// Thread.sleep()은 checked exception인 InterruptedException을 발생시키므로 try-catch문이
	// 필요합니다.
	public static void slowPrint(String text, long delayMillis) {
		for (char ch : text.toCharArray()) {
			System.out.print(ch);
			try {
				Thread.sleep(delayMillis);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println();
	}
	
	public static void screenClear() {
        for (int i = 0; i < 30; i++) System.out.println(); // 공백 30줄 출력
    }

	public void execute1() {
		Scanner scn = new Scanner(System.in);

		boolean run = true;
		while (run) {
			screenClear();
			slowPrint("==========================================", 20); // 20ms 간격으로 한 글자씩 출력
			System.out.println("-----------📦물품관리 프로그램📦--------------");
			System.out.println("------------------------------------------");
			System.out.println("----|1. 물품관리|2.-------|3. 프로그램종료|----");
			System.out.println("------------------------------------------");
			System.out.println("==========================================");
			System.out.print("메뉴선택 ▶");

			// 메뉴 선택.
			int menu = scn.nextInt();
			scn.nextLine();
			switch (menu) {
			case 1: // 물품관리 실행.
				System.out.println("물품관리를 불러옵니다.");
				execute2();
				break;

			case 2: // 실행.
				screenClear();
				slowPrint("슬롯머신을 불러옵니다.", 20);
				executeGame();
				break;

			case 3:
				slowPrint("프로그램을 종료하는중.....................", 20);
				System.out.println("end of prog.");
				run = false;
			} // end of switch.
		} // end of while.
	} // end of execute1.

	public static String shorten1(String pname) {
		if (pname.length() > 4) {
			return pname.substring(0, 3) + "..";
		} else {
			return pname;
		}
	}

	public static String shorten2(String pcate) {
		if (pcate.length() > 4) {
			return pcate.substring(0, 3) + "..";
		} else {
			return pcate;
		}
	}

	public void execute2() {
		Scanner scn = new Scanner(System.in);
		ProductManagement mgm;

		// 2개중에 선택해서 기능을 실행할 수 있음.
//		mgm = new ProductManagementStream(); // 1) 파일에 저장.
		mgm = new ProductManagementDAO(); // 2) DB에 저장.
		boolean run = true;
		while (run) {
			slowPrint("==========================================", 20);
			System.out.println("----------------📦물품관리------------------");
			System.out.println("------------------------------------------");
			System.out.println("-------|1.물품추가|2.목록조회|3.상세보기|-------");
			System.out.println("------------------------------------------");
			System.out.println("-------|4.물품수정|5.물품삭제|6.물품검색|-------");
			System.out.println("------------------------------------------");
			System.out.println("----------------|7.이전메뉴|----------------");
			System.out.println("------------------------------------------");
			System.out.println("==========================================");
			System.out.print("선택 ▶");

			// 메뉴 선택.

			int menu = scn.nextInt();
			scn.nextLine();
			switch (menu) {
			case 1: // 물품등록.
				System.out.println("물품번호 입력▷");
				int pno = Integer.parseInt(scn.nextLine());
				System.out.print("물품명 입력▷ ");
				String pname = scn.nextLine();
				System.out.print("물품 카테고리 입력▷ ");
				String pcate = scn.nextLine();
				System.out.print("가격 입력▷ ");
				int pcost = Integer.parseInt(scn.nextLine());
				System.out.print("수량 입력▷ ");
				int pquan = Integer.parseInt(scn.nextLine());

				// parameter(Product)
				Product product = new Product(pno, pname, pcate, pcost, pquan);

				if (mgm.addProduct(product)) {
					slowPrint("✅등록 성공✅",50);
				} else {
					slowPrint("❌등록 실패❌",50);
				}
				break;
			case 2: // 물품 목록.
				screenClear();
				slowPrint("물품의 전체목록을 불러옵니다.", 20);
				List<Product> list = mgm.productList();

				System.out.println("[No][ 물품명 ][카테고리][물품가격(원)][물품수량(EA)]");
				System.out.println("-----------------------------------------------");
				for (Product pdt : list) {
					String short1 = shorten1(pdt.getPname());
//					String short2 = shorten2(pdt.getPcate());
					System.out.printf("[%-2s] %-5s %-6s %-10d   %-10d \n"//
							, pdt.getPno()//
							, short1//
							, pdt.getPcate()
//							, short2//
							, pdt.getPcost()//
							, pdt.getPquan());
				}
				System.out.println("-----------------------------------------------");
				break;
			case 3: // 물품 상세보기.
				System.out.println("준비중입니다.");
				break;
			case 4: // 물품 정보 수정하기.
				screenClear();
				list = mgm.productList();
				System.out.println("[No][ 물품명 ][카테고리][물품가격(원)][물품수량(EA)]");
				System.out.println("-----------------------------------------------");
				for (Product pdt : list) {
					String short1 = shorten1(pdt.getPname());
//					String short2 = shorten2(pdt.getPcate());
					System.out.printf("[%-2s] %-5s %-6s %-10d   %-10d \n"//
							, pdt.getPno()//
							, short1//
							, pdt.getPcate()
//							, short2//
							, pdt.getPcost()//
							, pdt.getPquan());
				}
				System.out.println("-----------------------------------------------");
				System.out.print("✏️수정할 물품번호▷ ");
				pno = Integer.parseInt(scn.nextLine());

				System.out.print("수정할 물품명 입력▷ ");
				pname = scn.nextLine();
				System.out.print("수정할 카테고리 입력▷ ");
				pcate = scn.nextLine();
				System.out.print("수정 가격 입력▷ ");
				pcost = Integer.parseInt(scn.nextLine());
				System.out.print("수정 수량 입력▷ ");
				pquan = Integer.parseInt(scn.nextLine());
				product = new Product(pno, pname, pcate, pcost, pquan);
				//
				product.setPname(pname);
				product.setPcate(pcate);
				product.setPcost(pcost);
				product.setPquan(pquan);

//				Product productRenew = new Product();
//				productRenew.setPno(pno);
				if (mgm.modifyProduct(product)) {
					slowPrint("✅수정 완료✅",50);
				} else {
					slowPrint("❌수정 실패❌",50);
				}
				break;

			case 5: // 물품 삭제하기.
				screenClear();
				list = mgm.productList();
				System.out.println("[No][ 물품명 ][카테고리][물품가격(원)][물품수량(EA)]");
				System.out.println("-----------------------------------------------");
				for (Product pdt : list) {
					String short1 = shorten1(pdt.getPname());
//					String short2 = shorten2(pdt.getPcate());
					System.out.printf("[%-2s] %-5s %-6s %-10d   %-10d \n"//
							, pdt.getPno()//
							, short1//
							, pdt.getPcate()
//							, short2//
							, pdt.getPcost()//
							, pdt.getPquan());
				}
				System.out.println("-----------------------------------------------");
				System.out.print("🗑️삭제할 물품번호▷ ");
				pno = Integer.parseInt(scn.nextLine());
				if (mgm.removeProduct(pno)) {

					slowPrint("🗑️삭제 완료🗑️",50);
				} else {
					slowPrint("❌삭제 실패❌",50);
				}
				break;
			case 6: // 물품 검색하기.
				screenClear();
				System.out.println("검색할 카테고리를 입력하세요▷");
				pcate = scn.nextLine();
				List<Product> list1 = mgm.searchProduct(pcate);
				System.out.println("[No][ 물품명 ][카테고리][물품가격(원)][물품수량(EA)]");
				System.out.println("-----------------------------------------------");
				for (Product pdt : list1) {
					String short1 = shorten1(pdt.getPname());
//					String short2 = shorten2(pdt.getPcate());
					System.out.printf("[%-2s] %-5s %-6s %-10d   %-10d \n"//
							, pdt.getPno()//
							, short1//
							, pdt.getPcate()
//							, short2//
							, pdt.getPcost()//
							, pdt.getPquan());
				}
				System.out.println("-----------------------------------------------");
				break;
				

			case 7:
				slowPrint("이전메뉴로 돌아갑니다",50);
				run = false;
				mgm.save();
			} // end of switch.
		} // end of while.
	} // end of execute2.

//	public void execute3() {
//		Scanner scn = new Scanner(System.in);
//		ProductQuanManagement Qmgm;
//
//		boolean run = true;
//		while (run) {
//			slowPrint("==========================================", 30);
//			System.out.println("----------------📦수량관리------------------");
//			System.out.println("------------------------------------------");
//			System.out.println("-------|1.수량추가|2.수량수정|3.수량조회|-------");
//			System.out.println("------------------------------------------");
//			System.out.println("-------|4.수량삭제|5.이력조회|6.이전메뉴|-------");
//			System.out.println("------------------------------------------");
//			System.out.println("==========================================");
//			System.out.print("메뉴선택 ▶");
//
//			// 메뉴 선택.
//			int menu = scn.nextInt();
//			scn.nextLine();
//			switch (menu) {
//			case 1:
//
//			case 2:
//
//			case 3:
//
//			case 4:
//
//			case 5:
//
//			case 6:
//				System.out.println("프로그램을 종료합니다.");
//				run = false;
////				Qmgm.save();
//			} // end of switch.
//
//		} // end of while.
//
//	} // end of execute3.

	public void executeGame() {
		SlotMachine slot = new SlotMachine();
		slot.spin();
		slot.printBoard();
		SlotGame game = new SlotGame(99999); // 시작 돈 100원
		game.play();

		if (slot.checkWin()) {
			System.out.println("축하합니다! 상금을 받으세요!");
		} else {
			System.out.println("아쉽지만, 다시 도전하세요!");
		}
	}
}// end of class(ProductApp).
