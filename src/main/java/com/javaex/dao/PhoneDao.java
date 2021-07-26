package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession;
	//전체 리스트 가져오기
	public List<PersonVo> getPersonList(){
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		
		return personList;
	}
	
	//저장
	public int personInsert(PersonVo personVo) {
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return count;
	}
	
	//저장2
	public int personInsert2(String name, String hp, String company) {
		
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		Map<String, Object> personMap = new HashMap<String, Object>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		System.out.println("personMap :" + personMap);
		int count = sqlSession.insert("phonebook.personInsert2", personMap);
		System.out.println(count + "건 등록되었습니다.(Dao결과)");
		return count;
	}
	
	//삭제
	public int personDelete(int personId) {
		
		int count = sqlSession.delete("phonebook.personDelete", personId);
	
		return count;
	}
	
	//수정폼
	public PersonVo getPerson(int personId) {
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectPerson",personId);
		
		return personVo;
	}
	
	//수정폼2
		public Map<String, Object> getPerson2(int personId) {
			
			Map<String, Object> personMap =  sqlSession.selectOne("phonebook.selectPerson2",personId);
			System.out.println(personMap);
			
			return personMap;
		}
	
}

