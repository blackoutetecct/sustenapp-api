package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.UsuarioRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioExists {
    private final UsuarioRepository usuarioRepository;

    public boolean isValid(UUID value) {
        return existsUsuario(value);
    }

    private boolean existsUsuario(UUID usuario){
        return usuarioRepository.existsById(usuario);
    }
}
