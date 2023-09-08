package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.UsuarioRepository;

@Component
@RequiredArgsConstructor
public class EmailExists {
    private final UsuarioRepository usuarioRepository;

    public boolean isValid(String value) {
        return existsUsuarioByEmail(value);
    }

    private boolean existsUsuarioByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
