package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.ComodoRepository;
import sustenapp_api.repository.RecuperacaoRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecuperacaoExists {
    private final RecuperacaoRepository recuperacaoRepository;

    public boolean isValid(String email, String codigo) {
        return existsRecuperacao(email, codigo);
    }

    private boolean existsRecuperacao(String email, String codigo){
        return recuperacaoRepository.existsByEmailAndCodigo(email, codigo);
    }
}
