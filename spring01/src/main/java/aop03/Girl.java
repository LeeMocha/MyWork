package aop03;

import java.util.Random;

public class Girl implements Programmer {

	@Override
	public void doStudying() throws Exception {
		
		System.out.println("열심히 게시판을 만듭니다. => 핵심적 관심사항");
		
		// ** Test 를 위해 늘 실패하도록 처리
		// => 항상 true 가 되도록
//		if( new Random().nextBoolean() ) {
		if(true) {
			// Random().nextBoolean() 가 true 이면 실패
			throw new Exception("~~ Error 500 * 100 => 예외 발생");
		}
		
	} // doStudying
	
} // class
