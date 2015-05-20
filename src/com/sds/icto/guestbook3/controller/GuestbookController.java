package com.sds.icto.guestbook3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook3.domain.GuestbookVo;
import com.sds.icto.guestbook3.repository.GuestbookDao;

@Controller
public class GuestbookController {

	@Autowired
	GuestbookDao guestbookDao;

	@RequestMapping(value={"","/","/list","/index"})
	public String index(Model model) {
		List<GuestbookVo> list = guestbookDao.selectGuestBook();
		model.addAttribute("list", list);
		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo vo) {
		guestbookDao.insertGuestBook(vo);
		return "redirect:/index";
	}

	@RequestMapping(value = {"/delete/{no}"}, method = RequestMethod.GET)
	public String delete(@RequestParam("no") Long no, @RequestParam("pass") String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		guestbookDao.deleteGuestBook(vo);
		return "redirect:/index";
	}
}