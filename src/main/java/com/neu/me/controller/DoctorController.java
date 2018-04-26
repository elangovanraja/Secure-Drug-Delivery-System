package com.neu.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.me.DAO.DoctorDAO;
import com.neu.me.DAO.MedicineDAO;
import com.neu.me.DAO.PersonDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Medicines;
import com.neu.me.pojo.Patient;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.PresMedicines;
import com.neu.me.pojo.Prescriptions;
import com.neu.me.pojo.person;


@Controller
public class DoctorController {

	@RequestMapping(value="/writePrescription", method=RequestMethod.GET)
	public ModelAndView writePrescription(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="patient1")String patient) {
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		ModelAndView model = new ModelAndView();
		model.addObject("patient",patient);
		MedicineDAO medicineDao = new MedicineDAO();
		List<Medicines> listOfMedicines = new ArrayList<Medicines>();
		try {
			System.out.println(listOfMedicines.size());
			listOfMedicines = medicineDao.getMedicine();
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addObject("medicine", listOfMedicines);
		model.setViewName("WritePrescription");
		
		return model;
	}
	
	@RequestMapping(value="/viewPatient", method=RequestMethod.GET)
	public ModelAndView AddPharmacy5(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctors doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicines medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		
		person user = (person)session.getAttribute("user");
		if(user==null){
			ModelAndView mv=new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;
			
		}
		int personId = user.getPersonId();
		
		DoctorDAO docDao = new DoctorDAO();
		List<Patient> listPatient=new ArrayList<Patient>();
		try {
			listPatient = docDao.getPatient(personId);
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(listPatient.size() == 0) {
			model.addObject("count",0);
		}
		else {
			model.addObject("count",1);
			model.addObject("patients",listPatient);
		}
		model.setViewName("ViewPatient");
		return model;
	}
	
	@RequestMapping(value="/homepage2", method=RequestMethod.GET)
	public ModelAndView AddPharmacy6(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctors doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicines medicine, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		model.setViewName("DoctorWorkArea");
		return model;
	}
	
	@RequestMapping(value="/pres", method=RequestMethod.GET)
	public ModelAndView AddPharmacy7(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctors doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicines medicine, BindingResult result) {
		String[] Listmedicines = request.getParameterValues("medicines");
		String[] medquantities = request.getParameterValues("medQuantity");
		
		HttpSession session=request.getSession();
		Prescriptions P=new Prescriptions();
		person D=(person) session.getAttribute("user");
		if(D==null){
			ModelAndView mv=new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;
			
		}
		P.setDoctor(D.getPersonId());
		
		PersonDAO persondao=new PersonDAO();
		int id=0;
		try {
			
			id=persondao.getAllPerson(request.getParameter("patient1000"));
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		P.setPatient(id);
		
		MedicineDAO mdao = new MedicineDAO();
		List<Medicines> list = null;
		try {
			list = mdao.getMedicine();
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] mId=new int[Listmedicines.length];
		for(int i=0;i<Listmedicines.length;i++) {
			for(Medicines m : list) {
			
				if(m.getMedName().equals(Listmedicines[i])) {
					mId[i]=m.getId();
				}
			}
			
		}
		
		
		for(int i=0;i<Listmedicines.length;i++) {
			PresMedicines pm = new PresMedicines();
			pm.setMedicineId(mId[i]);
			if(medquantities[i].equals("")){
			pm.setQuantity(0);
			}
			else{
			pm.setQuantity(Integer.parseInt(medquantities[i]));}
			P.getPresMedicinesSet().add(pm);
		}
		
		DoctorDAO dDao = new DoctorDAO();
		dDao.create(P);
		
		try {
			dDao.getAppointment(id, D.getPersonId());
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("PrescriptionProvided");
		return mv;
	}
	
	@RequestMapping(value="/pres", method=RequestMethod.POST)
	public ModelAndView AddPharmacy8(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("doctor")Doctors doctor, @ModelAttribute("pharmacy")Pharmacy pharmacy,@ModelAttribute("medicine")Medicines medicine, BindingResult result) {
		String[] Listmedicines = request.getParameterValues("medicines");
		String[] medquantities = request.getParameterValues("medQuantity");
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		System.out.println(Listmedicines[2]);
		System.out.println(medquantities[2]);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("PrescriptionProvided");
		return mv;
	}
	
}
