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

import com.neu.me.DAO.MedicineDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.Medicines;
import com.neu.me.pojo.person;

@Controller
@RequestMapping("/addMedicine")
public class AdminController3 {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AddPharmacy3(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("medicine") Medicines doctor1, BindingResult result) {

		MedicineDAO phardao = new MedicineDAO();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		Medicines p = null;
		try {
			p = phardao.getMed(doctor1.getMedName());
		} catch (PException e1) {
			// TODO Auto-generated catch block
		}
		if (p != null) {
			ModelAndView model = new ModelAndView();
			model.addObject("error1", 1);
			model.setViewName("addMedicine");
			return model;
		} else {
		try {
			phardao.create(doctor1.getMedName(), doctor1.getMedQuantity(), doctor1.getMedProposition());
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Medicines> listOfDoctors = new ArrayList<Medicines>();
		try {
			listOfDoctors = phardao.getMedicine();
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

		model.setViewName("addMedicine");
		return model;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddPharmacy4(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("medicine") Medicines doctor1, BindingResult result) {
		MedicineDAO docDAO = new MedicineDAO();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}

		List<Medicines> listOfDoctors = new ArrayList<Medicines>();
		try {
			listOfDoctors = docDAO.getMedicine();
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
		model.setViewName("addMedicine");
		return model;
	}

}
