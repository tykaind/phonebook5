package com.javaex.controller;

import java.util.List;
import java.util.Map;

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
		return "list";
	}

	// 쓰기폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("[writeForm]");

		return "writeForm";
	}

	// 쓰기
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("[write]");

		phoneDao.personInsert(personVo);

		return "redirect:/list";
	}

	// 쓰기2
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		int count = phoneDao.personInsert2(name, hp, company);

		return "redirect:/list";
	}

	// 리스트 삭제
	@RequestMapping("/delete")
	public String personDelete(@RequestParam("personId") int personId) {
		System.out.println("[delete]");

		phoneDao.personDelete(personId);

		return "redirect:/list";
	}

	// 리스트 수정폼
	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("personId") int personId, Model model) {
		System.out.println("[updateForm]");

		PersonVo personVo = phoneDao.getPerson(personId);

		model.addAttribute("updateOne", personVo);
		return "updateForm";
	}

	// 리스트 수정폼2
	@RequestMapping("/updateForm2")
	public String updateForm2(@RequestParam("personId") int personId, Model model) {
		System.out.println("[updateForm]");

		Map<String, Object> personMap = phoneDao.getPerson2(personId);

		model.addAttribute("pMap", personMap);
		return "updateForm2";
	}

	// 리스트 수정
	@RequestMapping("/update")
	public String update(@ModelAttribute PersonVo personVo) {

		// phoneDao.personUpdate(personVo);

		return "redirect:/list";
	}
}