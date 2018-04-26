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

import com.neu.me.DAO.PersonDAO;
import com.neu.me.DAO.PharmaDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.person;

@Controller
@RequestMapping("/addPharmacy")
public class AdminController1 {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView AddPharmacy1(HttpServletRequest request, HttpServletResponse response,
		@ModelAttribute("pharmacy") Pharmacy pharma, BindingResult result) {
		
		PharmaDAO phardao = new PharmaDAO();
		HttpSession session = request.getSession();
		PersonDAO personDao = new PersonDAO();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		person p = null;
		try {
			p = personDao.getPerson(pharma.getUserName());
		} catch (PException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (p != null) {
			ModelAndView model = new ModelAndView();
			model.addObject("error1", 1);
			model.setViewName("addPharmacy");
			return model;
		} else {
		try {
			phardao.create(pharma.getPwd(), pharma.getName(), "Pharmacy", pharma.getUserName(),
					pharma.getPharmaName(), pharma.getSsn());
		} catch (PException e) {
						e.printStackTrace();
		}
		List<Pharmacy> listOfPharmacies = new ArrayList<Pharmacy>();
		try {
			listOfPharmacies = phardao.getPharmacy();
		} catch (PException e) {

			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		if (listOfPharmacies.size() == 0) {
			model.addObject("count", 0);
		} else {
			model.addObject("count", 1);
			model.addObject("pharmacies", listOfPharmacies);

		}
		model.addObject("error1", 0);
		model.setViewName("addPharmacy");
		return model;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView AddPharmacy(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pharmacy") Pharmacy pharma, BindingResult result) {
		PharmaDAO pharmacy = new PharmaDAO();
		List<Pharmacy> listOfPharmacies = new ArrayList<Pharmacy>();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		try {
			listOfPharmacies = pharmacy.getPharmacy();
		} catch (PException e) {

			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		if (listOfPharmacies.size() == 0) {
			model.addObject("count", 0);
		} else {
			model.addObject("count", 1);
			model.addObject("pharmacies", listOfPharmacies);

		}

		model.setViewName("addPharmacy");
		return model;
	}

}
