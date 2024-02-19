package aop01;

import java.util.Random;

public class Girl implements Programmer {

	@Override
	public void doStudying() {
		
		System.out.println("프로젝트 과제를 합니다. => Before 핵심 작업을 하기 위한 준비 단계");
		
		try {
			System.out.println("열심히 게시판을 만듭니다. => 핵심적 관심사항");
			
			if( new Random().nextBoolean() ) {
				// Random().nextBoolean() 가 true 이면 실패
				throw new Exception("~~ Error 500 * 100 => 예외 발생");
			} else {
				// Random().nextBoolean() 가 false 이면 성공
				System.out.println("~~ 글등록이 잘 됩니다. => 핵심적 관심사항이 정상 종료 ");
			}
				
		} catch (Exception e) {
			System.out.println("** Girl Exception => "+ e.toString());
			System.out.println("** 밤새워 수정 합니다. zz~~ => 예외발생으로 핵심적 관심사항이 비정상 종료 ");
			
		} finally {
			System.out.println("** finally : 무조건 제출 합니다 ~~  => After(무조건 종료) 공통 관심사항 ");
			
		}
		
	} // doStudying
	
} // class
