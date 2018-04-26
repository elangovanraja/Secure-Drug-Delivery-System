package com.neu.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.me.DAO.DoctorDAO;
import com.neu.me.DAO.PersonDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.person;

@Controller
@RequestMapping("/addDoctor")
public class AdminController2 {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AddPharmacy2(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("doctor") Doctors doctor1, @ModelAttribute("pharmacy") Pharmacy pharmacy1,
			BindingResult result) {

		DoctorDAO phardao = new DoctorDAO();
		PersonDAO personDao = new PersonDAO();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		person p = null;
		try {
			p = personDao.getPerson(doctor1.getUserName());
		} catch (PException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (p != null) {
			ModelAndView model = new ModelAndView();
			model.addObject("error1", 1);
			model.setViewName("addDoctor");
			return model;
		} else {
			try {
				phardao.create(doctor1.getPwd(), doctor1.getName(), "Doctor", doctor1.getUserName(),
						doctor1.getSpecialized(), doctor1.getSsn());
			} catch (PException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Doctors> listOfDoctors = new ArrayList<Doctors>();
			try {
				listOfDoctors = phardao.getDoctor();
			} catch (PException e) {

				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView();
			if (listOfDoctors.size() == 0) {
				model.addObject("count", 0);
			} else {
				model.addObject("count", 1);
				model.addObject("doctors", listOfDoctors);

			}
			model.addObject("error1", 0);
			model.setViewName("addDoctor");
			return model;
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddPharmacy3(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("doctor") Doctors doctor1, @ModelAttribute("pharmacy") Pharmacy pharmacy1,
			BindingResult result) {
		DoctorDAO docDAO = new DoctorDAO();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}

		List<Doctors> listOfDoctors = new ArrayList<Doctors>();
		try {
			listOfDoctors = docDAO.getDoctor();
		} catch (PException e) {

			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		if (listOfDoctors.size() == 0) {
			model.addObject("count", 0);
		} else {
			model.addObject("count", 1);
			model.addObject("doctors", listOfDoctors);
		}
		model.setViewName("addDoctor");
		return model;
	}

}
