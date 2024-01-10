package j01_basic;

import jdbc02.JoDTO;
import jdbc02.StudentDTO;

class Store {
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
} // Store

//------------------------------------------------------------
//** Generic
//=> 컬렉션(Collection:자료구조) 을 보강해준 기능
//=> 컴파일 단계에서 객체의 자료형을 선언(정의) 해주면
// 다른 타입의 자료가 저장될수 없도록 해주는 기능

//** Generic 클래스 정의
//=> 클래스 이름 옆에 <> 사이에 알파벳 1글자를 사용하여 
// Generic Type 명을 선언해줌 
// ex : <T> 처럼 "<" 와 ">" 사이에 선언 
//=> 대문자로 T, E 등을 많이 사용 
// Type 의미로 "T" 를 주로 사용
//=> Generic 타입으로 클래스를 사용한다는 의미 
//=> 제네릭으로 기본 자료형(int, float, double....)은 사용할 수 없지만
// 기본자료형의 wrapper 클래스를 이용할 수있다. 

//** Generic 타입제한 (사용시, Wildcards_와일드카드타입 이용으로)
//=> <?>
// Unbounded Wildcards (제한없음_모든 클래스나 인터페이스 타입 가능)
//=> <? extends 클래스명> Ex) <? extends JoDTO> => JoDTO를 상속받은 하위의 모든 클래스타입
// Upper Bounded Wildcards (상위클래스 제한_같거나 하위 타입 가능)
//=> <? super 클래스명>		<? super StudentDTO> => StudentDTO를 자손으로 갖는 부모클래스인 모든 클래스, JoDTO, Object 
// Lower Bounded Wildcards (하위클래스 제한_ 같거나 상위타입 가능)

//=> 정의할때: <T> , <T extends 클래스명> , <T super 클래스명>
//------------------------------------------------------------


class StoreG<T>{		// 제네릭의 특성에 따라서 대문자 약어를 씀. 아직 정해지지 않은 타입이다 => T = type
//	 																			  E : Element
//	 																			  K : key
//	 																		      V : value
//	 																		      N : number
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
} // StoreG<T>

// ** 제네릭 클래스의 타입 인자 제한
class Box<T extends Number>{
	private T data;
	
	public void setData(T data) {this.data = data;}
	public T getData() {return this.data;}
} // box


class GenArray<T>{
	private T[] arr;

	public T[] getArr() {
		return arr;
	}

	public void setArr(T[] arr) {
		this.arr = arr;
	}
	
	public void printArr() {
		for(T t : arr) {
			System.out.println(t);
		} // for
	} // printArr
} // GenArray<T>



public class Gn01_StoreTest {

	public static void main(String[] args) {
		// 1. Object 를 이용한 기존 방식
		Store s1 = new Store();
		
		s1.setData("짜장면");
		s1.setData(123);		// int -> Integer	(자동 형 변환이 일어남 => auto Boxing) 
		s1.setData(123.456); 	// double -> Double (자동 형 변환이 일어남 => auto Boxing)
		s1.setData(123.456f);
		s1.setData(123456789L);
//		s1.setData(new JoDTO(7, "Banana", 77, "화이팅","Generic Test"));
		System.out.println("** Test 1 **" + s1.getData());
		
		// => 단점 Test 
//		String s = (String)s1.getData();
		// => 컴파일 오류 없으나 RunTime 오류가 발생 error: ClassCastException
		// 이렇게 만들다 보면 로직을 잘 못짜게 될 요지가 있음 
		// 컴파일 과정에선, 문법에서는 문제가 없기 때문에 그냥 넘어가게되지만
		// RunTime과정에서 오류가 발생하게 됨.
		// => 제네릭 타입의 필요성이 생김. 처음 들어간 타입에 따라서 그 타입으로 제한해서 쓰겠다.
//		System.out.println("** 단점 Test 1 **" + s);
		
		
		// 2. Generic StoreG
		StoreG g1 = new StoreG(); // 꺽쇄 (<>) : Type 생략시 Object 로 지정되어 1번과 같아짐
		
		// => 제네릭 클레스의 타입 인자 제한
		Box<Integer> b1 = new Box();
		b1.setData(123456);
//		Box<String> b2 = new Box(); 	// 컴파일 오류 발생
		
		// => 생성시 Type 제한 의 경우는 확인 필요함
		StoreG<? extends JoDTO> g11 = new StoreG();
//		g11.setData(new StudentDTO());
		StoreG<String> g2 = new StoreG<String>();
		g2.setData("스트링만 가능");
//		g2.setData(12345);  // 컴파일 오류 발생
		
		StoreG<Integer> g3 = new StoreG<Integer>();
		g3.setData(12345);
//		g3.setData("123456");// 컴파일 오류 발생
//		g3.setData(123.456); // 컴파일 오류 발생
		
		
		// 3. GenArray Test
		// 	1) String
		String[] ss = {"가","나", "Do", "Re", "Mi"};
		GenArray<String> ga1 = new GenArray<String>();
		ga1.setArr(ss);
		ga1.printArr();
		
		// 	2) Integer
		Integer[] ii = {1,2,3,4,5};
		GenArray<Integer> ga2 = new GenArray<Integer>();
		ga2.setArr(ii);
		ga2.printArr();
		
		// 	3) Character (char)
		Character[] cc = {'a', 'b', 'c', '디', '이'};
		GenArray<Character> ga3 = new GenArray<Character>();
		ga3.setArr(cc);
		ga3.printArr();
		
		// 	4) 객체
		JoDTO[] jj = {new JoDTO(), new JoDTO(), new JoDTO(), new JoDTO(), new JoDTO()};
		GenArray<JoDTO> ga4 = new GenArray<JoDTO>();
		ga4.setArr(jj);
		ga4.printArr();
		
	} // main
} // class 
