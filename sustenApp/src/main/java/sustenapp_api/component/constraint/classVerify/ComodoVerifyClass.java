package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import sustenapp_api.component.constraint.annotation.ComodoVerify;
import sustenapp_api.repository.ComodoRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class ComodoVerifyClass implements ConstraintValidator<ComodoVerify, UUID> {
    private final ComodoRepository comodoRepository;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext constraintValidatorContext) {
        return existsComodo(value);
    }

    private boolean existsComodo(UUID comodo){
        return comodoRepository.existsById(comodo);
    }
}