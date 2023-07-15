package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import sustenapp_api.component.constraint.annotation.UsuarioEmailVerify;
import sustenapp_api.repository.UsuarioRepository;

@RequiredArgsConstructor
public class UsuarioEmailVerifyClass implements ConstraintValidator<UsuarioEmailVerify, String> {
    private final UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return existsUsuarioByEmail(value);
    }

    private boolean existsUsuarioByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}