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
import org.springframework.web.servlet.ModelAndView;

import com.neu.me.DAO.AppointmentDAO;
import com.neu.me.DAO.DoctorDAO;
import com.neu.me.DAO.MedicineDAO;
import com.neu.me.DAO.PharmaDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.Appointmnet;
import com.neu.me.pojo.Doctors;
import com.neu.me.pojo.Medicines;
import com.neu.me.pojo.PharmaMedicine;
import com.neu.me.pojo.Pharmacy;
import com.neu.me.pojo.PresMedicines;
import com.neu.me.pojo.Prescriptions;
import com.neu.me.pojo.person;



@Controller
public class PharmacyController {

	@RequestMapping(value = "/deliverMed", method = RequestMethod.GET)
	public ModelAndView doSubmitAction2(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pharmacy") Pharmacy pharmacy1, BindingResult result) {
		ModelAndView model = new ModelAndView();
		HttpSession session = request.getSession();
		person user = (person) session.getAttribute("user");
		if(user==null){
			ModelAndView mv=new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;
			
		}
		int presId = Integer.parseInt(request.getParameter("selectPres"));
		MedicineDAO mDAO = new MedicineDAO();
		List<PharmaMedicine> list = new ArrayList<PharmaMedicine>();
		list = mDAO.getPharMed(user.getPersonId());
		List<PresMedicines> listPres = new ArrayList<PresMedicines>();
		listPres = mDAO.getPresMed(presId);
		if (list.size() == 0 || listPres.size() == 0) {
			model.setViewName("consulted");
			return model;
		}
		int flag = 0;
		for (PresMedicines pm1 : listPres) {
			for (PharmaMedicine pm : list) {
				if ((pm1.getMedicineId() == pm.getMedicineId()) && (pm1.getQuantity() > pm.getQuantity())) {
					flag = 1;
					break;
				}

			}
		}
		if (flag == 0) {
			for (PresMedicines pm1 : listPres) {
				int flag2 = 0;
				for (PharmaMedicine pm : list) {
					if (pm1.getMedicineId() == pm.getMedicineId())
						flag2 = 1;
				}
				if (flag2 == 0) {
					flag = 1;
					break;
				}
			}
		}

		if (flag == 0) {

			DoctorDAO d = new DoctorDAO();
			Prescriptions p = null;
			try {
				p = d.getPrescription(presId);
			} catch (PException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int patient = p.getPatient();
			int doctor = p.getDoctor();
			try {
				d.getAppointment2(patient, doctor);
			} catch (PException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setViewName("success");
		} else {
			model.setViewName("fail");
		}
		return model;
	}

	@RequestMapping(value = "/addToStore", method = RequestMethod.GET)
	public ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("pharmacy") Pharmacy pharmacy1, BindingResult result) {
		ModelAndView model = new ModelAndView();
		MedicineDAO medicineDao = new MedicineDAO();

		HttpSession session = request.getSession();
		Pharmacy pharmacy = (Pharmacy) session.getAttribute("user");
		if(pharmacy==null){
			ModelAndView mv=new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;
			
		}
		List<Medicines> list = new ArrayList<Medicines>();
		List<PharmaMedicine> listOfMed = new ArrayList<PharmaMedicine>();
		try {
			list = medicineDao.getMedicine();
			listOfMed = medicineDao.getPharMed(pharmacy.getPersonId());
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			model.addObject("count", 0);
		} else {
			model.addObject("count", 1);
			model.addObject("medicines", list);

		}
		model.addObject("pharMed", listOfMed);
		model.setViewName("PurchaseMedicines");
		return model;
	}

	@RequestMapping(value = "/added", method = RequestMethod.GET)
	public ModelAndView doSubmitAction3(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView model = new ModelAndView();
		 HttpSession session = request.getSession();
		Pharmacy ph = (Pharmacy) session.getAttribute("user");
		if(ph==null){
			ModelAndView mv=new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;
			
		}
		int pharmacyId = ph.getPersonId();

		String med = request.getParameter("m");

		String quantity = request.getParameter("qty");

		MedicineDAO mDAO = new MedicineDAO();
		List<Medicines> list = new ArrayList<Medicines>();
		try {
			list = mDAO.getMedicine();
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int medicineId = 0;
		List<PharmaMedicine> pm = mDAO.getPharMed1(med);
		
		
		if (pm.size()==0) {
			for (Medicines m : list) {
				if (m.getMedName().equals(med)) {
					mDAO.insertPharmacyMedicine(Integer.parseInt(quantity), m.getId(), ph.getPersonId());
				}

			}
		} else {
			for (Medicines m : list) {
				if (m.getMedName().equals(med)) {
					
					mDAO.updatePharmacyMedicine(Integer.parseInt(quantity) + pm.get(0).getQuantity(), m.getId(),
							ph.getPersonId());
					
				}
			}

		}

		// HttpSession session = request.getSession();
		Pharmacy pharmacy = (Pharmacy) session.getAttribute("user");
		// List<Medicine> list = new ArrayList<Medicine>();
		List<PharmaMedicine> listOfMed = new ArrayList<PharmaMedicine>();
		try {
			list = mDAO.getMedicine();
			listOfMed = mDAO.getPharMed(pharmacy.getPersonId());
			System.out.println(listOfMed.size());
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			model.addObject("count", 0);
		} else {
			model.addObject("count", 1);
			model.addObject("medicines", list);

		}
		model.addObject("pharMed", listOfMed);
		model.setViewName("PurchaseMedicines");
		return model;
	}

	@RequestMapping(value = "/deliver", method = RequestMethod.GET)
	public ModelAndView doSubmitAction2() {

		AppointmentDAO apDAO = new AppointmentDAO();
		List<Appointmnet> appoints = apDAO.getAppoints();
		List<Appointmnet> consulted = new ArrayList<Appointmnet>();
		for (Appointmnet A : appoints) {
			if (A.getStatus().equals("consulted")) {
				consulted.add(A);
			}
		}
		PharmaDAO phDAO = new PharmaDAO();
		List<Prescriptions> listOfPs = phDAO.getPrescriptions();
		List<Prescriptions> consultedPrescriptions = new ArrayList<Prescriptions>();
		for (Appointmnet A : consulted) {
			for (Prescriptions B : listOfPs) {
				if (A.getDoctor() == B.getDoctor() && A.getPatient() == B.getPatient()) {
					consultedPrescriptions.add(B);
				}
			}
		}
		ModelAndView mv = new ModelAndView("consulted", "prescription", consultedPrescriptions);
		return mv;
	}

	@RequestMapping(value = "/homepage3", method = RequestMethod.GET)
	public ModelAndView AddPharmacy5(@ModelAttribute("doctor") Doctors doctor,
			@ModelAttribute("pharmacy") Pharmacy pharmacy, @ModelAttribute("medicine") Medicines medicine,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		model.setViewName("PharmacyWorkArea");
		return model;
	}

}
