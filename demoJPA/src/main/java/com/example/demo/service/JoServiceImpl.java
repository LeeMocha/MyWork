package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Jo;
import com.example.demo.repository.JoRepository;

import lombok.AllArgsConstructor;

//** Service
//=> 요청클래스 와 mapper클래스 사이의 연결(완충지대) 역할
//=> 요청클래스(컨트롤러) 와 mapper클래스 사이에서 변경사항이 생기더라도 서로 영향   받지않도록해주는 역할
// 결합도는 낮추고, 응집도는 높인다

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...

@Service
@AllArgsConstructor
public class JoServiceImpl implements JoService {

	JoRepository repository;
	
	public List<Jo> selectList() {
		return repository.findAll();
	}

	@Override
	public Jo selectOne(int jno) {
		Optional<Jo> result = repository.findById(jno);
		if(result.isPresent()) return result.get();
		else return null;
	}

	@Override
	public Jo save(Jo dto) {
		
		return repository.save(dto);
	}

	@Override
	public void deleteById(int jno) {
		repository.deleteById(jno);
	}

	
}
