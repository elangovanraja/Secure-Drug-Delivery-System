package com.neu.me.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Medicines;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.person;


@Controller
public class AdminController4 {

	@RequestMapping(value="/homepage", method=RequestMethod.GET)
	
	public ModelAndView AddPharmacy5(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctors doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicines medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		model.setViewName("ControllerWorkArea");
		return model;
	}
}
