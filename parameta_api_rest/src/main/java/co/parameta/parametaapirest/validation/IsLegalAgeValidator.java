package co.parameta.parametaapirest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class IsLegalAgeValidator implements ConstraintValidator<IsLegalAge, LocalDate> {

    @Value("${application.employee.legal_age}")
    private int legalAge;

    @Override
    public void initialize(IsLegalAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        int currentAge = Period.between(dateOfBirth, now).getYears();
        return currentAge >= legalAge;
    }
}
