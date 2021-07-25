package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {
	
	@Autowired
	private PhoneDao phoneDao;
	
	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[list]");

		List<PersonVo> personList = phoneDao.getPersonList();
		model.addAttribute("personList", personList);
		return "/WEB-INF/views/list.jsp";
	}

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[writeForm]");

		return "/WEB-INF/views/writeForm.jsp";
	}

	
	 //쓰기
	 @RequestMapping(value = "/write", method = { RequestMethod.GET,RequestMethod.POST }) 
	 public String write(@ModelAttribute PersonVo personVo){ 
		 System.out.println("[write]");
		 
		 phoneDao.personInsert(personVo);

		 return "redirect:/list"; 
	 }
	  
	  //리스트 삭제
	  @RequestMapping("/delete")
	  public String personDelete(@RequestParam("personId") int personId) {
		  System.out.println("[delete]");
		  
		  //phoneDao.personDelete(personId);
		  
		  
		  return "redirect:/list";
	  }
	  
	  //리스트 수정폼
	  @RequestMapping("/updateForm")
	  public String updateForm(@RequestParam("personId") int personId ,Model model) {
		  System.out.println("[updateForm]");
		  
		  //PersonVo updateOne = phoneDao.getPerson(personId);

		 // model.addAttribute("updateOne", updateOne);
		  return "/WEB-INF/views/updateForm.jsp";
	  }
	  
	  //리스트 수정
	  @RequestMapping("/update")
	  public String update(@ModelAttribute PersonVo personVo) {
		  System.out.println("[update]");
		 
		  //phoneDao.personUpdate(personVo);
		  
		  
		  return "redirect:/list";
	  }
}