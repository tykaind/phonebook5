package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.vo.PersonVo;
import com.javax.dao.PhoneDao;

@Controller
public class PhoneController {

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[list]");

		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		//값을 model 담는다 -->dispatcherServlet 에전달된다 --- request의 attritube영역에 넣는다.
		model.addAttribute("personList", personList);
		
		//view
		return "/WEB-INF/views/list.jsp";
	}

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[writeForm]");

		return "/WEB-INF/views/writeForm.jsp";
	}

	
	 //파라미터 한번에 받기 쓰기
	 @RequestMapping(value = "/write", method = { RequestMethod.GET,RequestMethod.POST }) 
	 public String write(@ModelAttribute PersonVo personVo){ 
		 System.out.println("[write]");
		 
		 PhoneDao phoneDao = new PhoneDao();
		 phoneDao.personInsert(personVo);
		 //@ModelAttribute --> new PersonVo() // --> 기본생성자 + setter
	  
		 System.out.println(personVo); 
		 return "redirect:/list"; 
	 }
	  
	  //리스트 삭제
	  @RequestMapping("/delete")
	  public String personDelete(@RequestParam("personId") int personId) {
		  System.out.println("[delete]");
		  
		  PhoneDao phoneDao = new PhoneDao();
		  phoneDao.personDelete(personId);
		  
		  
		  return "redirect:/list";
	  }
	  
	  //리스트 수정폼
	  @RequestMapping("/updateForm")
	  public String updateForm(@RequestParam("personId") int personId ,Model model) {
		  System.out.println("[updateForm]");
		  
		  PhoneDao phoneDao = new PhoneDao();
		  PersonVo updateOne = phoneDao.getPerson(personId);

		  model.addAttribute("updateOne", updateOne);
		  return "/WEB-INF/views/updateForm.jsp";
	  }
	  
	  //리스트 수정
	  @RequestMapping("/update")
	  public String update(@ModelAttribute PersonVo personVo) {
		  System.out.println("[update]");
		  
		  PhoneDao phoneDao = new PhoneDao();
		  phoneDao.personUpdate(personVo);
		  
		  
		  return "redirect:/list";
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  /*
	   //주소관련 예제 @PathVariable-테스트
	  @RequestMapping(value="/board/read/{no}", method = {RequestMethod.GET,RequestMethod.POST})
	  public String read(@PathVariable("no") int boardNo) {
		  System.out.println("PathVariable [read]");

		  System.out.println(boardNo);
		  
		  return "";
	  }
	
	  //파라미터를 1개씩 받을때 , 파라미터값이 안넘어와서 변수가없을때
	  //쓰기
	  @RequestMapping(value = "/write", method = { RequestMethod.GET,RequestMethod.POST }) 
	 public String write(@RequestParam("name") String name, 
			  			  @RequestParam("hp") String hp,
			  			  @RequestParam(value="company", required=false, defaultValue="-1") String company){
		 System.out.println("[write]");
	  
		 PersonVo personVo = new PersonVo(name, hp, company);
		 System.out.println(personVo);
		 PhoneDao phoneDao = new PhoneDao();
		 phoneDao.personInsert(personVo);
		 
		 return "redirect:/list";
	  }
	  
	//테스트용
	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("TEST");

		return "/WEB-INF/views/test.jsp";
	}
	*/
}
