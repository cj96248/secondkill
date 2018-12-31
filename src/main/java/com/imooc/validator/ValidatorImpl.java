package com.imooc.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean{

    private Validator validator;

    public ValidationResult validate(Object object){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> set = validator.validate(object);
        if(set.size() > 0){
            result.setHasErrors(true);
            set.forEach(violation ->{
                String errorMessage = violation.getMessage();
                String name = violation.getPropertyPath().toString();
                result.getErrorMessageMap().put(name, errorMessage);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
