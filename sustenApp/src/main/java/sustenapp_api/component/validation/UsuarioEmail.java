package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import sustenapp_api.repository.UsuarioRepository;

@RequiredArgsConstructor
public class UsuarioEmail {
    private final UsuarioRepository usuarioRepository;

    public boolean isValid(java.lang.String value) {
        return existsUsuarioByEmail(value);
    }

    private boolean existsUsuarioByEmail(java.lang.String email){
        return usuarioRepository.existsByEmail(email);
    }
}
