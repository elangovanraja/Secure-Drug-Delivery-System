package com.neu.me.controller;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.me.pojo.Pharmacy;

import org.springframework.validation.ValidationUtils;

@Component
public class PharmacyValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Pharmacy.class);
    }

    public void validate(Object obj, Errors errors)
    {
    	Pharmacy user = (Pharmacy) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pharmaName", "error.invalid.pharmaName", "pharmacyName Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pharmaManager", "error.invalid.pharmaManager", "pharmacyManager Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "error.invalid.ssn", "ssn Required");
    }
}
