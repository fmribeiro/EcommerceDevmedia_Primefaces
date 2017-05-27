/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Administrador
 */
public class ValidPostalCodeImpl implements ConstraintValidator<ValidPostalCode, String> {

    @Override
    public void initialize(ValidPostalCode constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pat = Pattern.compile("[0-9]{5}-[0-9]{3}");
        Matcher m = pat.matcher(value);
        return m.matches();
    }
    
}
