package co.parameta.parametaapirest.validation;

import co.parameta.parametaapirest.constant.ErrorConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsLegalAgeValidator.class)
public @interface IsLegalAge {

    String message() default ErrorConstant.IS_NOT_LEGAL_AGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
