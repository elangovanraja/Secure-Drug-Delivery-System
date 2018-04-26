package com.neu.me.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.me.pojo.Patient;

import org.springframework.validation.ValidationUtils;

public class PatientValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Patient.class);
	}

	public void validate(Object obj, Errors errors) {
		Patient user = (Patient) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.name", "Person Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "error.invalid.pwd", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "Username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "error.invalid.weight", "weight Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "blood_Group", "error.invalid.blood_Group",
				"bloodGroup Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "BP", "error.invalid.BP", "bloodPressure Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastVisit", "error.invalid.lastVisit",
				"lastVisitDate Required");

	}
}
