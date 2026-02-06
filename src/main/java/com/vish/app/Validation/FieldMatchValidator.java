package com.vish.app.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

	private String firstField;
	private String secondField;
	private String message;
	
	// override initialize with custom fields to constraint annotation
	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstField = constraintAnnotation.first();
		secondField = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}
	
	// compare match object, return  true if valid
	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			final Object firstObject = new BeanWrapperImpl(value).getPropertyValue(firstField);
			final Object secondObject = new BeanWrapperImpl(value).getPropertyValue(secondField);
			valid = firstObject == null && secondObject == null || firstObject != null && firstObject.equals(secondObject);
		}
		catch (final Exception ignore) {
			// TODO: handle exception
		}
		
		if(valid) {
			context
				.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(firstField)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		}
		
		return valid;
	}
}
