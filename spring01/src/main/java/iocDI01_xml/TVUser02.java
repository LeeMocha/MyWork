package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


//** 스프링이 제공하는 BeanFactory
//=> 스프링 컨테이너
//=> AbstractApplicationContext 와 GenericXmlApplicationContext 계층도 
//=> Object -> DefaultResourceLoader (C) -> AbstractApplicationContext (A) 
//       -> GenericApplicationContext (C) -> GenericXmlApplicationContext (C) 

//public abstract class AbstractApplicationContext extends DefaultResourceLoader
//    implements ConfigurableApplicationContext, DisposableBean {....

//public class GenericXmlApplicationContext extends GenericApplicationContext {...

//=> 컨테이너는 xml (설정화일), @, JavaCode (JavaConfig) 등의 
//전달사항을 통해 요구하는 클래스를 생성, 보관, 제공

//** xml (설정화일)
// => 설정화일(xml구문_요구사항 목록) 을 매개변수로 전달
// => GenericXmlApplicationContext("app02.xml");
//    xml 문을 "src/main/resources"  에 두면 패키지는 생략가능 

public class TVUser02 {

	public static void main(String[] args) {
		// 1. BeanFactory ( 스프링 컨테이너 ) 생성
		AbstractApplicationContext sc = new GenericXmlApplicationContext("iocDI01_xml/app02.xml");
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
		
	    // 3. singleton(싱글톤) Test
	    // => 스프링 프레임웤의 모든 작업은 싱글톤을 기본으로함.
	    // => 싱글톤 (한개의 인스턴스만 허용 하는것) 적용 Test
	    // => 설정화일의 scope 속성 에 "prototype"_ss / "singleton"_lg (default 는 싱글톤)
	    // => 생성자 실행횟수와 아래의 주소값 확인해보기
	    //    SsTVi 2개, LgTVi(prototype) 2개 씩 인스턴스 작성 후 확인
		TV tv1 = (TV)sc.getBean("tv");
		TV tv2= (TV)sc.getBean("tv");
		System.out.println("** prototype(프로토타입) Test **");
		System.out.println("** tv => " + tv);
		System.out.println("** tv1 => " + tv1);
		System.out.println("** tv2 => " + tv2);
		System.out.println();
		// 싱글톤 이여서 인스턴스가 세개가 다 주소가 똑같음.
		System.out.println("** singleton(싱글톤) Test **");
		TV tvs1 = (TV)sc.getBean("tvs");
		TV tvs2 = (TV)sc.getBean("tvs");
		System.out.println("** tvs1 => " + tvs1);
		System.out.println("** tvs2 => " + tvs2);
		
		sc.close();
		
	} // main
	
} // class
