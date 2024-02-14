package pageTest;

import lombok.Getter;
import lombok.ToString;

//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> 1Page당 표시할 pageNo갯수
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo
//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowsPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

//=> Criteria 를 이용해서..

@Getter
@ToString
public class PageMaker {
	
	private int totalRowsCount; // 출력대상 전체 row 갯수 : from DB
	private int displayPageNo = 3; // 1Page 당 표시할 Page 갯수 
	private int spageNo; // View 에 표시할 첫 PageNo : 개산 필요
	private int epageNo; // View 에 표시할 마지막 PageNo : 계산 필요
	// sPageNo 가 안되는 이유는 2번째 알파벳이 대문자인 경우 setter/getter 되는 경우 오류가 발생할 수 있음
	// ex) setSPageNo or setsPageNo 
	//   Lombok.. 등등 과 규칙이다르므로 사용시 불편 
	//   -> 그러므로 대.소문자 섞어사용시 주의. 
	private int lastPageNo; // 출력 가능한 전체 마지막 PageNo : 게산 필요
	private boolean prev; // 이전 PageBlock 으로
	private boolean next; // 다음 Pageblock 으로
	
	Criteria cri; 
	
	// ** 필요한 값 계산
	// 1) Criteria
	public void setCri (Criteria cri) {
		this.cri = cri;
	}
	
	// 2) totalRowsCount
	// => 전체 Rows의 갯수 : Read from DB
	// => 이 값을 이용한 나머지 필요한 값 계산 : 
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount = totalRowsCount;
		calcData();
	}
	
	// 3) CalculateData : 나머지 필요한 값 계산
	public void calcData() {
		// 3.1) spageNo , epageNo
		// => currPage 속한 pageBlock 의 spageNo 와 epageNo 값 계산

		// => n 개 씩 출력할 경우 epageNo 는 항상 n의 배수
		//  ex) n = 3 이면 첫번째 페이지 블럭 1,2,3 두번째 페이지 4,5,6 ...
	    // 3.1) spageNo, epageNo
	    // => currPage가 속한 PageBlock 의 spageNo, epageNo 를 계산 
	    
	    // => pageNo를 n개씩 출력한다고 가정하면 epageNo 는 항상 n의 배수
	    //     displayPageNo=3 이면 3, 6, 9, 12,......
	    // => ex) 17 page 요청 , displayPageNo=3, 일때 17이 몇번째 그룹 인지 계산하려면,
	    //        17/3 -> 5 나머지 2 결론은 정수 나누기 후 나머지가 있으면 +1 이 필요함
	    //    -> Math.ceil(17/3) -> Math.ceil(5.73..) -> 6.0 -> 6번쨰 그룹 16,17,18
	    // 즉, 17이 몇번째 그룹 인지 계산하고, 여기에 * displayPageNo 를 하면 됨.   
	    
	    // ** Math.ceil(c) : 매개변수 c 보다 크면서 같은 가장 작은 정수를 double 형태로 반환 
	    //                   ceil -> 천장, 예) 11/3=3.666..  -> 4
	    // => Math.ceil(12.345) => 13.0      
		this.epageNo = (int)Math.ceil(cri.getCurrPage()/(double)displayPageNo)*displayPageNo;
		this.spageNo = (this.epageNo - displayPageNo) + 1;
		// => 요청받은 currPageNo 11 인 경우 epageNo -> (int)Math.ceil( 11 / 3 ) * 3 = 12
		// 								 spageNo -> 12 - 3 + 1 = 10 -> 결론은 11은 10, 11, 12 블럭에 속함
		
		// 3.2) lastPageNo 계산 & ePageNo의 적합성 
		this.lastPageNo = (int)Math.ceil(this.totalRowsCount/(double)cri.getRowsPerPage());
		if(this.epageNo > this.lastPageNo) this.epageNo = this.lastPageNo;
		
		// 3.3) prev, next
		prev = this.spageNo == 1 ? false : true;
		next = this.epageNo == this.lastPageNo ? false : true; 
		
	}
	
} // class
