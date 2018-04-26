package com.neu.me.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.me.DAO.AppointmentDAO;
import com.neu.me.DAO.DoctorDAO;
import com.neu.me.DAO.PersonDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Medicines;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.person;



@Controller
public class PatientController {

	@RequestMapping(value="/appointment", method=RequestMethod.GET)
	public ModelAndView AddAppointment1(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("doctor")Doctors doctor1, BindingResult result) {
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
			listOfDoctors=docDAO.getDoctor();
			
		} catch (PException e) {
			
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		if(listOfDoctors.size() == 0) {
			model.addObject("count",0);
		}
		else {
			
			model.addObject("count",1);
			model.addObject("doctors", listOfDoctors);
		}
		model.setViewName("AddAppointment");
		return model;
	}
	
	
	
	@RequestMapping(value="/appointmentConfirmation", method=RequestMethod.GET)
	public ModelAndView AddAppointment2(@RequestParam(value = "doctor1", required = true) String doc,
			HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("doctor")Doctors doctor1, BindingResult result) {
		AppointmentDAO appDao = new AppointmentDAO();
		HttpSession session = request.getSession();
		person user = (person)session.getAttribute("user");
		
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		int personId=user.getPersonId();
		DoctorDAO docDao = new DoctorDAO();
		List<Doctors> listOfDoctors = null;
		try {
			listOfDoctors = docDao.getDoctor();
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int doctorId =0;
		for(Doctors d : listOfDoctors) {
			if(d.getUserName().equals(doc)) {
				doctorId = d.getPersonId();
			}
		}
		try {
			appDao.createAppointment(doctorId, personId);
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();
		model.setViewName("AppointmentSuccess");
		return model;
			
		
	}
	
	@RequestMapping(value="/homepage1", method=RequestMethod.GET)
	public ModelAndView AddPharmacy5(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("doctor")Doctors doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicines medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		model.setViewName("PatientWorkArea");
		return model;
	}
	
	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public void ajax(HttpServletRequest request, HttpServletResponse response) {
		
		
		String s1=request.getParameter("q");
		PersonDAO p1 = new PersonDAO();
		person p=null;
		try {
			p = p1.getPerson(s1);
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj=null;
		if(p != null){
			obj=new JSONObject();
			try {
				obj.put("msg", "Username unavailable");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//s="Username Available";
		}
		else {
			obj=new JSONObject();
			try {
				obj.put("msg", "Username available");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//s="Username unavailable";
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(obj);
		
	}
}
