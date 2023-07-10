package sustenapp_api.component.constraint.classVerify;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;
import sustenapp_api.repository.UsuarioRepository;

import java.util.UUID;

public class UsuarioVerifyClass implements ConstraintValidator<UsuarioVerify, UUID> {
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext constraintValidatorContext) {
        return existsUsuario(value);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }
}