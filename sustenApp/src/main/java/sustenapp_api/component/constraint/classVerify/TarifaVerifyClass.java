package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import sustenapp_api.component.constraint.annotation.TarifaVerify;
import sustenapp_api.repository.TarifaRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class TarifaVerifyClass implements ConstraintValidator<TarifaVerify, UUID> {
    private final TarifaRepository tarifaRepository;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext constraintValidatorContext) {
        return existsTarifa(value);
    }

    private boolean existsTarifa(UUID tarifa){
        return tarifaRepository.existsById(tarifa);
    }
}