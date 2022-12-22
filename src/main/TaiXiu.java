package main;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/* Một người chơi sẽ có tài khoản. 
 * Người chơi có quyền đặt cược số tiền ít hơn hoặc bằng số tiền hoặc bằng số tieefn họ đang có 
 * Rules:
 * Có 3 viên xúc xắc. Mỗi viên có 6 mặt có giá trị từ 1 đến 6.
 * Mỗi lần lắc sẽ ra 1 kết quả. Kết quả = giá trị xx1 + giá trị xx2 + giá trị xx3
 * TH1: Nếu tổng = 3 hoặc 18 => Cái ăn hết, người chơi thua.
 * TH2: Nếu tổng = (4 - 10) <-> Xỉu => nếu người chơi đặt Xỉu thì ngchoi thắng, ngc lại thua.
 * TH3: Nếu tổng = (11-17) <-> Tài  => nếu người chơi đặt Tài thì ngchoi thắng, ngc lại thua.
 * */

public class TaiXiu {
	public static void main(String[] args) {
		double taiKhoanNguoiChoi = 5000000;
		Scanner sc = new Scanner(System.in);
	
		Locale lc = new Locale("vi", "VN");
		NumberFormat numf = NumberFormat.getInstance(lc);
//		NumberFormat numf = NumberFormat.getCurrencyInstance(lc);

		int luaChon = 0;
		do {
			System.out.println("--------MOI BAN LUA CHON-------");
			System.out.println("Chon (1) de tiep tuc choi.");
			System.out.println("Chon so khac de thoat.");
			luaChon = sc.nextInt();
			if(luaChon == 1) {
				System.out.println("*** BAT DAU CHOI: ");
				
				//Đặt cược:
				System.out.println("***** Tai khoan cua ban la: " + numf.format(taiKhoanNguoiChoi) + " VND, ban muon cuoc bao nhieu? ");
				double datCuoc = 0;
				do {
					System.out.println("****** Dat cuoc (0<so tien cuoc<="+numf.format(taiKhoanNguoiChoi)+"(nhap -1 de all in)):");
					if(datCuoc == -1) {
						datCuoc = taiKhoanNguoiChoi;
					}
				} while (datCuoc <=0 || datCuoc > taiKhoanNguoiChoi);
				
				//Lựa chọn Tài Xỉu
				int luaChonTaiXiu = 0;
				do {
					System.out.println("****** Chon: 1<->Tai hoac 2<->Xiu");
					luaChonTaiXiu = sc.nextInt();
				} while (luaChonTaiXiu!=1&&luaChonTaiXiu!=2);
				
				//Tung Xúc xắc
				Random xucXac1= new Random();
				Random xucXac2= new Random();
				Random xucXac3= new Random();
				
				int giaTri1 = xucXac1.nextInt(5) + 1;
				int giaTri2 = xucXac2.nextInt(5) + 1;
				int giaTri3 = xucXac3.nextInt(5) + 1;
				int tong = giaTri1 + giaTri2 + giaTri3;
				
				//Tính toán kết quả:
				System.out.println("****** Ket qua: " +giaTri1+ "-" +giaTri2+ "-" +giaTri3);
				if(tong==3 || tong==18) {
					taiKhoanNguoiChoi -= datCuoc; 
					System.out.println("****** Tong la: " +tong+ "=> Nha cai an het, ban da thua!");
					System.out.println("****** Tai khoan hien tai: " +numf.format(taiKhoanNguoiChoi));
				}else if (tong>=4 && tong<=10) {
					System.out.println("Tong la: " +tong+ " => Xiu");
					if(luaChonTaiXiu==2) {
						System.out.println("***** Chuc mung ban da thang cuoc!");
						taiKhoanNguoiChoi += datCuoc;
						System.out.println("****** Tai khoan hien tai: " +numf.format(taiKhoanNguoiChoi));
					}else {
						System.out.println("***** Ban da thua cuoc");
						taiKhoanNguoiChoi -= datCuoc;
					}
				}else {
					System.out.println("Tong la: " +tong+ " => Tai");
					if(luaChonTaiXiu==1) {
						System.out.println("***** Chuc mung ban da thang cuoc!");
						taiKhoanNguoiChoi += datCuoc;
						System.out.println("****** Tai khoan hien tai: " +numf.format(taiKhoanNguoiChoi));
					}else {
						System.out.println("***** Ban da thua cuoc");
						taiKhoanNguoiChoi -= datCuoc;
					}
				}
			}
			
		} while (luaChon == 1);
	}
}
