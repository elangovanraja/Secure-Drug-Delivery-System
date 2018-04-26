package com.neu.me.controller;
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

import com.neu.me.DAO.PersonDAO;
import com.neu.me.Exception.PException;
import com.neu.me.pojo.person;

@Controller
public class LoginController {
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView();
		HttpSession session =request.getSession();
		session.invalidate();
		model.setViewName("login");
		System.out.println("hellooo");
		return model;
		
	}
	@RequestMapping(value = "/workArea", method = RequestMethod.GET)
	public ModelAndView login3(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		person user = (person) session.getAttribute("user");
		ModelAndView model = new ModelAndView();
		if (user == null) {
			ModelAndView mv = new ModelAndView();
			session.invalidate();
			mv.setViewName("login");
			return mv;

		}
		else{
			if(user.getType().equals("Person")) {
            	model.addObject("type", "Person");
            	model.setViewName("PatientWorkArea");
            }
            else if(user.getType().equals("Controller")){
            	model.addObject("type", "Controller");
            	
            	model.setViewName("ControllerWorkArea");
            }
            else if(user.getType().equals("Pharmacy")) {
            	model.addObject("type", "Pharmacy");
            	
            	model.setViewName("PharmacyWorkArea");
            }
            else if(user.getType().equals("Doctor")) {
            	model.addObject("type", "Doctor");
            	
            	model.setViewName("DoctorWorkArea");
            }
			return model;
		}
		
	}
	@RequestMapping(value = "/workArea", method = RequestMethod.POST)
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		HttpSession session=request.getSession();
		ModelAndView model = new ModelAndView();
		PersonDAO persondao=new PersonDAO();
		person user=null;
		try {
		
			model.addObject("error", false);
			user=persondao.getPerson(username);
			if(user != null) {
				if(user.getPwd().equals(password)){
					session.setAttribute("user", user);
					model.addObject("loggedinUser", user.getName());
					model.addObject("person",user);
					if(user.getType().equals("Person")) {
                    	model.addObject("type", "Person");
                    	model.setViewName("PatientWorkArea");
                    }
                    else if(user.getType().equals("Controller")){
                    	model.addObject("type", "Controller");
                    	
                    	model.setViewName("ControllerWorkArea");
                    }
                    else if(user.getType().equals("Pharmacy")) {
                    	model.addObject("type", "Pharmacy");
                    	
                    	model.setViewName("PharmacyWorkArea");
                    }
                    else if(user.getType().equals("Doctor")) {
                    	model.addObject("type", "Doctor");
                    	
                    	model.setViewName("DoctorWorkArea");
                    }
				}
				else {
					
					model.addObject("error", "Invalid Credentials");
					model.setViewName("login");
				}
			}
			else {
				model.addObject("error", "Invalid Credentials!!!");
				model.setViewName("login");
			}
		} catch (PException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;

	}
}
