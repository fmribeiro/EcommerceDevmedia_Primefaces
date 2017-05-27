/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Administrador
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=ValidPostalCodeImpl.class)
public @interface ValidPostalCode {
    
    String message() default "The Postal Code is not Valid";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
