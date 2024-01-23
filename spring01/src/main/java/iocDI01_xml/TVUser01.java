package iocDI01_xml;


// ** Test1. 절차지향
class SsTV {
	public void turnOn () {
		System.out.println("~~ SsTV turnOn ~~");
	}
	public void turnOff () {
		System.out.println("~~ SsTV turnOff ~~");
	}
	public void soundUp () {
		System.out.println("~~ SsTV soungUp ~~");
	}
	public void soundDown () {
		System.out.println("~~ SsTV soundDown ~~");
	}
} // SsTV
class LgTv {
	public void powerOn () {
		System.out.println("~~ LgTV powerOn ~~");
	}
	public void powerOff () {
		System.out.println("~~ LgTV powerOff ~~");
	}
	public void volumeUp () {
		System.out.println("~~ LgTV volumeUp ~~");
	}
	public void volumeDown () {
		System.out.println("~~ LgTV volumeDown ~~");
	}
} // LgTv

// ** Test2. 객체지향 : 다형성 적용
// => interface, 구현을 강제 (메서드명에 규칙이 생기고 동일해 짐)
interface TV {
	void powerOn ();
	void powerOff ();
	void volumeUp ();
	void volumeDown ();
}

class SsTVi implements TV {
	public SsTVi() {System.out.println("~~ SsTVi 기본생성자 ~~");}
	public void powerOn () {System.out.println("~~ SsTVi powerOn ~~");};
	public void powerOff () {System.out.println("~~ SsTVi powerOff ~~");};
	public void volumeUp () {System.out.println("~~ SsTVi volumeUp ~~");};
	public void volumeDown () {System.out.println("~~ SsTVi volumeDown ~~");};
}
class LgTVi implements TV {
	public LgTVi() {System.out.println("~~ LgTVi 기본생성자 ~~");}
	public void powerOn () {System.out.println("~~ LgTVi powerOn ~~");};
	public void powerOff () {System.out.println("~~ LgTVi powerOff ~~");};
	public void volumeUp () {System.out.println("~~ LgTVi volumeUp ~~");};
	public void volumeDown () {System.out.println("~~ LgTVi volumeDown ~~");};
}


// ** Test3. Factory 패턴 (IOC/DI 입문)
// => getBean 메서드의 매개변수로 요청을 전달
class BeanFactory{
	
	public TV getBean (String tv) {
		if(tv != null && tv.equals("ss")) {
			return new SsTVi();
		} else if (tv != null && tv.equals("lg")) {
			return new LgTVi();
		} else return null;
	}
}



public class TVUser01 {

	public static void main(String[] args) {
		// ** Test1. 절차지향
		System.out.println("** Test1. 절차지향 **");
//		SsTV tv = new SsTV();
//		tv.turnOn();
//		tv.soundDown();
//		tv.soundUp();
//		tv.turnOff();
		// => TV 교체 필요 : 완전 다시 코드 작성해야함
		LgTv tv = new LgTv();
		tv.powerOn();
		tv.volumeDown();
		tv.volumeUp();
		tv.powerOff();
		System.out.println();
	    // ** Test2. 객체지향
	    // => 기본특징 : 캡슐화, 상속, 추상, 다형성(*)
		// => 캡슐화 : private , 정보 은닉
		// => 상속 : 
		// => 추상 : abstract , 규칙성 부여
		// => 다형성 : 하나의 메서드, 하나의 객체가 다양한 형태로 실행되는 성질
	    // => 다형성 적용
	    //    -> 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌, 규칙성 부여
	    //   -> 인터페이스에 정의된 메서드만 사용했다는 의미 (그러므로 교체가능)
	    //   -> TV 교체 필요 : 우측의 클래스만 교체 (단, 소스코드수정-재컴파일 이 필요함)
		System.out.println("** Test2. 객체지향 **");
		TV tvi = new SsTVi(); // SsTVi();, LgTVi();
		// 우변 인스턴트 생성이 다른게 와도 메서드는 인터페이스 TV의 메서드를 구현, 오버라이딩 한것이기 때문에
		// 하나의 메서드로 다른 기능을 볼 수 있음
		// 이것이 다형성!
		tvi.powerOn();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.powerOff();
		System.out.println();
	    // ** Test3. 소스코드 수정없이 실시간 객체 교체
	    // => 객체를 생성해서 교체해줄 역할자가 필요 : Factory 패턴 (IOC/DI 입문)
	    // => user 클래스의 요구사항(필요한 클래스, 의존성_Dependency) 을 Factory 에게 전달하는방법
	    // => 3가지 : xml, @, JavaConfig (JavaCode)
		System.out.println("** Test3. Factory 패턴 (IOC/DI 입문) **");
		BeanFactory bf = new BeanFactory();
		TV tvf = bf.getBean(args[0]);
		// => 소스코드 수정없이 실시간으로 요청을 전달 받음 : 임시로 main의 매개변수 args 활용
	    //    main의 매개변수가 null 인경우에는 args[0] 의 값이 없기 때문에
	    //     java.lang.ArrayIndexOutOfBoundsException
		if(tvf!=null) {
			tvf.powerOn();
			tvf.powerOff();
			tvf.volumeDown();
			tvf.volumeUp();
		} else System.out.println("** TV 를 선택 오류. **");
		
		
	} // main
	
} // class
