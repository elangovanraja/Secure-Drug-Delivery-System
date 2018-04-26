package com.neu.me.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.me.DAO.PersonDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.person;




@Controller
@RequestMapping("/signUp.htm")
public class PersonController {
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("patient")person person, BindingResult result) throws Exception {
		//personValidator.validate(person, result);
		PersonDAO personDao = new PersonDAO();
		int flag=0;
		ModelAndView model = new ModelAndView();
		person p = personDao.getPerson(person.getUserName());
		
		if(p != null) {
			flag = 1;
			model.addObject("error1", flag);
			model.setViewName("signUp");
			return model;
		}
		String type="Person";
		
		try {
			
			personDao.create(person.getName(),  person.getUserName(), person.getPwd(),person.getEmail(), type);
			flag=0;
			//session.setAttribute("error1", flag);
			model.addObject("error1",flag);
		}
		catch(PException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		model.setViewName("addedUser");
		return model;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("patient")person person, BindingResult result) {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("signUp");
		return model;
	}
}
