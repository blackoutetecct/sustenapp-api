package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sustenapp_api.component.constraint.annotation.TarifaVerify;
import sustenapp_api.repository.TarifaRepository;

import java.util.UUID;

public class TarifaVerifyClass implements ConstraintValidator<TarifaVerify, UUID> {
    @Autowired private TarifaRepository tarifaRepository;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext constraintValidatorContext) {
        return existsTarifa(value);
    }

    private boolean existsTarifa(UUID tarifa){
        return tarifaRepository.existsById(tarifa);
    }
}