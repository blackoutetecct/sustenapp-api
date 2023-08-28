package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import sustenapp_api.repository.UsuarioRepository;

@RequiredArgsConstructor
public class UsuarioEmail {
    private final UsuarioRepository usuarioRepository;

    public boolean isValid(String value) {
        return existsUsuarioByEmail(value);
    }

    private boolean existsUsuarioByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
