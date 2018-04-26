package com.neu.me.controller;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.me.pojo.Medicines;

import org.springframework.validation.ValidationUtils;


@Component
public class MedicineValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(Medicines.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Medicines user = (Medicines) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medName", "error.invalid.medName", "medicineName Required");
       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medQuantity", "error.invalid.medQuantity", "medicineQuantity Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "medProposition", "error.invalid.medProposition", "medicineProposition Required");
    }
}
