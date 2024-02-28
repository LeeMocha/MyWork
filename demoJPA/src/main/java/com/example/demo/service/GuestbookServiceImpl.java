package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


//** JPA 의 CRUD 메서드
//=> ~Repository 를 통해 JPA 의 EntityManager 에 접근됨.
//=> EntityManager : 영속 계층에 접근하여 엔티티에 대한 DB 작업을 제공함.
//=> 주요 메서드
//   - Insert : save(엔티티 객체)
//   - Select : findAll(), findById(키), getOne(키) ..
//   - Update : save(엔티티 객체)
//   - Delete : deleteById(키), delete(엔티티 객체)
//=> Insert, Update 모두 save(엔티티 객체)를 사용하는 이유
//   - JpaRepository 의 save는 비교후 없으면 insert, 
//     있으면 update를 동작시키고, entity를 return.   
//   - deleteById(키) 삭제의 경우에도 select 후 없으면 ~~DataAccessException 발생시키고
//     있으면 삭제하고 void 로 정의되어 return값 없음. 


@Service
@Log4j2
@RequiredArgsConstructor
//=> 각필드에 대해 1개의 매개변수가 있는 생성자를 생성함.
//=> 초기화 되지 않은 모든 final 필드에만 적용됨. 
public class GuestbookServiceImpl implements GuestbookService {
	
	private final GuestbookRepository repository;
	// => final 값인데 초기값을 안줘서 오류가 날 것임.
	// => JPA Sql 처리를 위해 정의
	// 	  생성자 주입 ( @RequiredArgsConstructor 를 통해 주입받음 )
	
	// ** JPA Pageable 을 이용한 Paging기능
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO) {
		
		// => 조건 완성
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

		// => repository 실행
		Page<Guestbook> result = repository.findAll(pageable);
		
//		Function<Guestbook, GuestbookDTO> fn = entity -> entityToDto(entity);
		// Function 인터페이스는 함수형 인터페이스
		// 그러므로 익명객체 문법, 람다를 사용하여 fn 초기화
		// entity 와 함께 entity를 dto 로 바꿔주는 entityToDto메서드 전달 
		// 람다식을 인자로 직접 사용해도 됨 (이로서 JAVA 도 함수형 언어라 할 수 있게 됨)
		
		return new PageResultDTO<>( result, entity -> entityToDto(entity) );
		
	}
	
	@Override
	public List<Guestbook> selectList() {
		return repository.findAll();
	}
	
	@Override
	public Guestbook selectOne(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		// repository.findById(gno)의 return 타입은 Optional<?>
		// ** Optional<T>
	    // => Java8부터 Optional<T>클래스를 사용해 NullPointerException(이하 NPE)를 방지할수 있도록 지원.
	    //     즉, Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스로, 참조하더라도 NPE가 발생하지 않도록 도와줌.
	    //     제공되는 메소드로 복잡한 조건문 없이 NPE를 회피할 수 있어록 해줌
	    // => isPresent() : Optional객체에 저장된 값이 null인지 확인(true:데이터 있음 , false : null)
	    // => get() : Optional객체에 저장된 값 제공
	    // => 참고 https://esoongan.tistory.com/95
		
		if( result.isPresent() ) return result.get();
		else return null;
	}
	
	// => insert, update 모두 사용
	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info("** register, dto => " + dto);
		
		Guestbook entity = dtoToEntity(dto);
		// 조상인 GuestbookService 인터페이스에 default 메서드로 구현되어있음
		
		repository.save(entity); // 처리 후 S entity 리턴,
		//즉 자기 entity가 return된 값으로 초기화 됨
		
		return entity.getGno(); // 저장된 키값을 return
	}
	
	// => delete 
	@Override
	public void delete(Long gno) {
		repository.deleteById(gno);
	}
	
} // class
