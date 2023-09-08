package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.UsuarioRepository;

@Component
@RequiredArgsConstructor
public class EmailOrCPFExists {
    private final UsuarioRepository usuarioRepository;

    public boolean isValid(String email, String cpf) {
        return existsUsuarioByEmailOrCpf(email, cpf);
    }

    private boolean existsUsuarioByEmailOrCpf(String email, String cpf){
        return usuarioRepository.existsByEmailOrCpf(email, cpf);
    }
}
