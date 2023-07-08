package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sustenapp_api.component.constraint.annotation.UsuarioEmailVerify;
import sustenapp_api.repository.UsuarioRepository;

public class UsuarioEmailVerifyClass implements ConstraintValidator<UsuarioEmailVerify, String> {
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return existsUsuarioByEmail(value);
    }

    private boolean existsUsuarioByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}