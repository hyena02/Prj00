package cafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

O1001,최지우,A,5,4500.0,Y
O1002,김서연,L,2,5200.0,N
O1003,박하준,D,3,10000.0,Y
O1004,박하준,T,3,6800.0,Y
O1005,박준,D,4,3800.0,N
quit

//주문금액 = 수량 * 단가
//포장비   = 포장여부가 Y이면 주문금액의 3%, N이면 0원
//최종금액 = 주문금액 + 포장비
//메뉴명   = A:아메리카노, L:라떼, T:차, D:디저트
//포장상태 = Y:포장, N:매장


//금액은 소수이하 두자리로 반올림
//모든 기능은 class 에 구현한다.

// 입력 data : 주문번호,    고객명,   메뉴코드,  수량,     단가,	포장여부
//	          : num ,    name  , mCode, amount,   price ,pack

// 출력 data : 주문번호, 고객명,    *메뉴명,	*주문금액,    *포장비,	*최종금액,   *포장상태
// 	           num  , name  , mName  ,   order ,     packFee  , kum,   packcon
interface IPO {
	void input();
	void process();
	void output();
}
class CafeVo {
//Field
	private String num;
	private String name;
	private char mCode;
	private int qty;
	private double price;
	private char pack;
	
	private String mName;
	private double order;
	private double packFee;
	private double kum;
	private String packCon;
// Constructor
	public CafeVo(String num, String name, char mCode, int qty, double price, char pack, String mName, double order,
			double packFee, double kum, String packCon) {
		super();
		this.num = num;
		this.name = name;
		this.mCode = mCode;
		this.qty = qty;
		this.price = price;
		this.pack = pack;
	}
//Getter
		public String getNum() {return num;}
		public String getName() {return name;}
		public char getMCode() {return mCode;}
		public int getQty() {return qty;}
		public double getPrice() {return price;}
		public char getPack() {return pack;}
		public String getMName() {return mName;}
		public double getOrder() {return order;}
		public double getPackFee() {return packFee;}
		public double getKum() {return kum;}
		public String getPackCon() {return packCon;}
	//Setter
		public void setNum(String num) {this.num = num;}
		public void setName(String name) {this.name = name;}
		public void setMCode(char mCode) {this.mCode = mCode;}
		public void setQty(int qty) {this.qty = qty;}
		public void setPrice(double price) {this.price = price;}
		public void setPack(char pack) {this.pack = pack;}
		public void setMName(String mName) {this.mName = mName;}
		public void setOrder(double ordKum) {this.order = ordKum;}
		public void setPackFee(double packFee) {this.packFee = packFee;}
		public void setKum(double kum) {this.kum = kum;}
		public void setPackCon(String packCon) {this.packCon = packCon;}
//toString
		@Override
		public String toString() {
			return "CafeVo [num=" + num + ", name=" + name + ", mCode=" + mCode + ", qty=" + qty + ", price=" + price
					+ ", pack=" + pack + ", mName=" + mName + ", order=" + order + ", packFee=" + packFee + ", kum="
					+ kum + ", packCon=" + packCon + "]";
		}//toString
	}//classVo
// ---------------------------------------------------------------------------------------------------

	
class CafeOrder implements IPO{
	List<CafeVo> cafeList = new ArrayList<>();
	
	@Override
	public void input() {
		Scanner in = new Scanner(System.in);
		System.out.println("입력하세요(주문번호, 고객명, 메뉴코드, 수량, 단가, 포장여부");
	
		while ( true) {				
			String line = in.nextLine();
			if(line.equals("quit") )break;	//종료조건

			String [] li = line.trim().split(",");
			String num		= li[0].trim();
			String name		= li[1].trim();
			char mCode	= li[2].toUpperCase().charAt(0);
			int qty			= Integer.parseInt(li[3].trim());
			double price	= Double.parseDouble(li[4].trim());
			char pack		= li[5].toUpperCase().charAt(0);
			
			
			CafeVo cafeVo 	= new CafeVo (num, name, mCode, qty, price, pack);
			cafeList.add(cafeVo);

//			System.out.println(cafeVo);
		}//while 문
	
	}//input

	@Override
	public void process() {
		for (int i = 0; i < cafeList.size(); i++) {
			CafeVo vo = cafeList.get(i);
			
//주문금액 = 수량 * 단가
			int qty = vo.getQty();
			double price = vo.getPrice();
			double order = qty * price;
			vo.setOrder(order);
			
//포장비   = 포장여부가 Y이면 주문금액의 3%, N이면 0원				
			double packFee = 0.0;
			if(vo.getPack() == 'Y')
				packFee = vo.getOrder() * 0.03;
//최종금액 = 주문금액 + 포장비
			double kum = order * packFee;
			vo.setKum( kum ); 
//메뉴명   = A:아메리카노, L:라떼, T:차, D:디저트
			Map<Character , String> map = new HashMap<>();
			map.put('A', "아메리카노");
			map.put('B', "라떼");
			map.put('C', "차");
			map.put('D', "디저트");
			char mCode = vo.getMCode();
			String mName = map.get(mCode);
			vo.setMName(mName);
//포장상태 = Y:포장, N:매장
			String packCon = (vo.getPackCon()=='Y')? "포장": "매장";
			vo.setPackCon(packCon);
			
			  
		}//for
		
	}// process

	@Override
	public void output() {
		String title = ""
		
		String fmt = "%-5s %-5s %-5s %.2f %.2f %.2f %-5s";
		for (CafeVo cafeVo : cafeList) {
				String num	= cafeVo.getNum();
				String name = cafeVo.getName();
				String mName = cafeVo.getMName();
				double order = cafeVo.getOrder();
				double packFee = cafeVo.getPackFee();
				double kum = cafeVo.getKum();
				String packCon = cafeVo.getPackCon();
				String msg = String.format(fmt, num, name,mName,order, packFee, kum, packCon);
		System.out.println(title);
			
		}//for		
	}//output
}//CafeOrder

			
		
		
	
	
	
	
	
	
	

	
public class TestCafe {
	public static void main(String[] args) {
		
		CafeOrder cafeOrfer = new CafeOrder();
		cafeOrder.input();
		cafeOrder.process();
		cafeOrder.output();
		
	}//main
}//class