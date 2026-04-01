package test08;


import java.util.Scanner;

//금액은 소수이하 두자리로 반올림 %.2f

/*
100,사나,20110101,300.0,10      
200,모모,20120301,270.0,20      
300,정연,20091003,250.0,30      
400,나연,20110105,220.0,40      
500,미나,20180401,170.0,60      
600,쯔위,20150801,200.0,50      
*/

interface Ipo {
	void input();
	void process();
	void output();
}

class Sa {

//Field 입력 data : 사번,이름,입사일,월급,부서번호 (id,name,date,price,num)

	private String id;		//사번 
	private String name;	//이름
	private int	date;		//입사일	0
	private double price;	//월급	0
	private int tnum;		//부서번호
	
//Field 출력 data : 사번,이름,입사일,월급,보너스,수령액,부서명 (id,name,date,prc,bonus,kum,team)
	private	double bonus;	//보너스	 0
	private double kum;		//수령액   0
	private String tcode;	//부서명


//Constructor
	public Sa(String id, String name, int date, double price, int tnum) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.price = price;
		this.tnum = tnum;
	}//public Sa


//toString

//Getter/setter
public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getDate() {
		return date;
	}
	public double getPrice() {
		return price;
	}
	public int getTnum() {
		return tnum;
	}
	public double getBonus() {
		return bonus;
	}
	public double getKum() {
		return kum;
	}
	public String getTcode() {
		return tcode;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public void setKum(double kum) {
		this.kum = kum;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
}//class Sa

//class - 처리
//interface - Sa

class Salary implements Ipo{

	private Sa s;
	
//Override
	@Override
	public void input() {
		//입력 data : 사번,이름,입사일,월급,부서번호 (id,name,date,price,num)
		Scanner in 	   = new Scanner(System.in);
		System.out.println("사번,이름,입사일,월급,부서번호");
		
		
		String 	  line = in.nextLine();
		String[] 	li = line.split(",");
		
		String		id = li[0].trim();
		String 	  name =li[1].trim();
		int 	  date = Integer.parseInt(li[2].trim());
		double 	 price = Double.parseDouble(li[3].trim());
		int num 	   = Integer.parseInt(li[4].trim());
		
		
		s = new Sa(id, name, date, price, num);
	}

	@Override
	public void process() {
		
//입사년도 추출
//		LocalDateTime now = LocalDateTime.now();
//		int year = now.getYear();
		
		int workYear = 2026 - ( s.getDate() / 10000 );

		
		
//보너스 Bonus =  근무연수에 따라 월급의 0.5% 로 계산한다
		double setBonus =  s.getPrice() * 0.005 * workYear;
		
//수령액 kum   =  월급 + 보너스

		double setKum = s.getPrice() + setBonus;
		
//부서명 tcode  =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스
		int tnum = s.getTnum();
		String tcode = "";
		switch(tnum) {
			case 10 : tcode = "인사"; break;
			case 20 : tcode = "자재"; break;
			case 30 : tcode = "총무"; break;
			case 40 : tcode = "연구개발"; break;
			case 50 : tcode = "생산"; break;
			case 60 : tcode = "서비스"; break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + tnum);
		}//switch
		s.setBonus(setBonus);
		s.setKum(setKum);
		s.setTcode(tcode);	
	}//process

	@Override
	public void output() {
//출력 data : 사번,이름,입사일,월급,보너스,수령액,부서명 (id,name,date,price,bonus,kum,team)
		String title = "사번,이름,입사일,월급,보너스,수령액,부서명";
		String fmt =   "%s  %s  %d  %.2f %.2f  %.2f  %s \n";
		System.out.println(title);
		System.out.printf(fmt, s.getId(),s.getName(),s.getDate(),s.getPrice(),s.getBonus(),s.getKum(),s.getTcode());
		
//		
//		System.out.printf("%1s %5s %5s %6s %5s %5s %6s %5s\n",
//				"사번","이름","입사일","월급","보너스","수령액","부서명");
//		String fmt =   "%s  %s  %d  %.2f %.2f  %.2f  %s \n";
//		System.out.printf(fmt, s.getId(),s.getName(),s.getDate(),s.getPrice(),s.getBonus(),s.getKum(),s.getTcode());
	}//output
}
  

public class Prj82 {
	public static void main(String[] args) {
		
		Salary s = new Salary();
		
		s.input();
		s.process();
		s.output();
		
	}
}
