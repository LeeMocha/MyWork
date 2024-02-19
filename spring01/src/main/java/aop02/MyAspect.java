package aop02;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {

	public void myAround(ProceedingJoinPoint joinpoint) {
		// ** Before
		System.out.println("프로젝트 과제를 합니다. => Before 핵심 작업을 하기 위한 준비 단계");
		
		try {
			// ** 핵심기능 수행(PointCut 전달 받아서 호출)
			// => Around 메서드의 매개변수 (ProceedingJoinPoint Type) 을 통해 전달받아 수행
			
			joinpoint.proceed();
			// Exception 이 아니라 Throwable 로,
	        // => Throwable 로 예외처리를 해야함
	        //    Throwable - Exception - RuntimeException (UnChecked), IOException (Checked) : 개발자가 대응 가능한
	        //              - Error : 시스템에 치명적이고 개발자가 대응 할 수 없는,
			
			
			// ** 핵심적 관심사항 정상종료: After_returning  
			System.out.println("~~ 200 Ok 회원가입, 글등록이 잘 됩니다. => 핵심적 관심사항이 정상 종료 ");
				
		} catch (Throwable e) {

			// ** 핵심적 관심사항 비정상종료: After_throwing 
			System.out.println("** 밤새워 수정 합니다. zz~~ => 예외발생으로 핵심적 관심사항이 비정상 종료 ");
			
			// ** 핵심적 관심사항의 비정상종료 : After_throwing
	        // => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
	        // => main으로 전달하려면 예외발생시켜주면됨.
	        // throw new Exception(e) ;  // Exception 은 Checked -> try~catch 처리 해야함 
			throw new RuntimeException(e); // Unchecked 
			
		} finally {
			System.out.println("** finally : 무조건 제출 합니다 ~~  => After(무조건 종료) 공통 관심사항 ");
			
		}
		
	} // myAround
	
	
	
}
