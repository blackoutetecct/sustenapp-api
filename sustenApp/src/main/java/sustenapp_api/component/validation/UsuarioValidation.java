package sustenapp_api.component.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.UsuarioRepository;

import java.util.UUID;

@Component
public class UsuarioValidation {
    @Autowired private UsuarioRepository usuarioRepository;

    public boolean isValid(UUID value) {
        return existsUsuario(value);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }
}
