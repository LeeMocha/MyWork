package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Annotation, @, 애너테이션
//=> 컴파일러에게 알려줌 (지시어)

//** @ Annotation 애너테이션 활용방법 *********************

//1. xml 에 context 네임스페이스 추가 [NameSpaces] 이용
//=> Component-scan 설정  : 
//<context:component-scan base-package="iocDI02_anno" />
//  
//2. 소스코드 
//=> import 확인  : org.springframework.context.support.*;
//=> 생성을 원하는 TV 클래스 위에 @Component("tv") 설정

//=> @Component("tv") 
//<bean ....> </bean> -> xml
//new 생성자() -> Java Code

//3.Test : 오류 메시지 확인 하기
//=> @ Test1 생성 -> <bean ... />
//=> @ Test2 id명 미 지정(둘다 beanname 없이) 
//=> ...NoSuchBeanDefinitionException: 
//           No bean named 'tv' is defined ....
//=> @ Test3 id명 중복되는 경우 (둘다 beanname  ("tv") 지정 )  
//=> ...annotation.ConflictingBeanDefinitionException: ....
//  ...non-compatible bean definition of same name and class ...

interface TV {
	void powerOn ();
	void powerOff ();
	void volumeUp ();
	void volumeDown ();
}

// @Component : app05.xml에서 에노테이션을 검색. ()안에는 id가 들어감
//@Component("tv")
class SsTVi implements TV {
	public SsTVi() {System.out.println("~~ SsTVi 기본생성자 ~~");}
	public void powerOn () {System.out.println("~~ SsTVi powerOn ~~");};
	public void powerOff () {System.out.println("~~ SsTVi powerOff ~~");};
	public void volumeUp () {System.out.println("~~ SsTVi volumeUp ~~");};
	public void volumeDown () {System.out.println("~~ SsTVi volumeDown ~~");};
}

//@Component("ltv")
class LgTVi implements TV {
	public LgTVi() {System.out.println("~~ LgTVi 기본생성자 ~~");}
	public void powerOn () {System.out.println("~~ LgTVi powerOn ~~");};
	public void powerOff () {System.out.println("~~ LgTVi powerOff ~~");};
	public void volumeUp () {System.out.println("~~ LgTVi volumeUp ~~");};
	public void volumeDown () {System.out.println("~~ LgTVi volumeDown ~~");};
}

public class TVUser05 {

	public static void main(String[] args) {

		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI02_anno/app05.xml");
		// Application 을 위한 Context 
		// 컨테이너를 만들기 위한 추상 클래스와 구현체
		// 구현체 인자로 .xml 을 줘야하는데 패키지 명까지 같이 줘야 함
		// 인자로 보낸 .xml 에서 Bean 태그를 사용해서 Bean 생성 가능
	    // => Spring 컨테이너는 getBean 메서드를 실행해서 해당객체를 제공
	    // => 실시간으로 소스코드 수정없이 전달받음 
		
		// 2. 필요한 객체를 전달 받고 서비스 실행
		TV tv = (TV)sc.getBean("tv");
		// 컨테이너 안에 Bean 을 생성하는 메서드, return 타입은 Object
		if(tv!=null) {
			tv.powerOn();
			tv.powerOff();
			tv.volumeDown();
			tv.volumeUp();
		} else System.out.println("** TV 를 선택하지 않았습니다. **");
		System.out.println();
		
		
		System.out.println("** Program 정상종료 **");
		
		sc.close();
	} // main

} // class
