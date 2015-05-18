package com.sds.icto.guestbook3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook3.dao.guestbookdao;
import com.sds.icto.guestbook3.vo.guestbookvo;


@Controller
public class GuestbookController {

	@Autowired
	guestbookdao guestbookDao;

	@RequestMapping("/index")
	public String index(Model model) {
		List<guestbookvo> list = guestbookDao.fetchList();
		model.addAttribute("list", list);
		return "/views/index.jsp";
	}


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestParam("name") String name,
			@RequestParam("pass") String password,
			@RequestParam("content") String message) {
		guestbookvo vo = new guestbookvo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);

		guestbookDao.insert(vo);

		return "redirect:/index";

		// return "/views/form_emaillist.jsp";
	}

}
