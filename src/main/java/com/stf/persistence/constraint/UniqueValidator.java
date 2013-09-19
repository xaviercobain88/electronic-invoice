package com.stf.persistence.constraint;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.stf.business.facade.UserFacade;

public class UniqueValidator implements ConstraintValidator<Unique, String>{

	@EJB
	UserFacade userFacade;
	
	
	@Override
	public void initialize(Unique constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
