package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sustenapp_api.component.constraint.annotation.StringVerify;

public class StringVerifyClass implements ConstraintValidator<StringVerify, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.matches("^[a-zA-Z]+$");
    }
}