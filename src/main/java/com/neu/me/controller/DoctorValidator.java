package com.neu.me.controller;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.me.pojo.Doctors;

import org.springframework.validation.ValidationUtils;

@Component
public class DoctorValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Doctors.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Doctors user = (Doctors) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.name", "Person Name Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "error.invalid.pwd", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "Username Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "error.invalid.ssn", "SSN Required");
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "specialized", "error.invalid.specialized", "Specilization Required");

    }
}
