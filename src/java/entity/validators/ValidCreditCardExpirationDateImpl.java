/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.validators;

import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Administrador
 */
public class ValidCreditCardExpirationDateImpl implements ConstraintValidator<ValidCreditCardExpirationDate, Date>{

    @Override
    public void initialize(ValidCreditCardExpirationDate constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        Date actualDate = Calendar.getInstance().getTime();
        if(value == null){
            return true;
        }
        return actualDate.before(value);
    }
    
}
