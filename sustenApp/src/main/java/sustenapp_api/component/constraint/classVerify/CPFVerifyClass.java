package sustenapp_api.component.constraint.classVerify;

import br.com.caelum.stella.validation.CPFValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sustenapp_api.component.constraint.annotation.CPFVerify;

public class CPFVerifyClass implements ConstraintValidator<CPFVerify, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return verifyCpf(value);
    }

    private boolean verifyCpf(String cpf) {
        try{
            new CPFValidator().assertValid(cpf);
            return true;
        } catch (Exception ignored){}

        return false;
    }
}