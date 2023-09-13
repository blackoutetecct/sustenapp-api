package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.RecuperacaoRepository;

@Component
@RequiredArgsConstructor
public class EmailRecuperacaoExists {
    private final RecuperacaoRepository recuperacaoRepository;

    public boolean isValid(String email) {
        return existsRecuperacao(email);
    }

    private boolean existsRecuperacao(String email){
        return recuperacaoRepository.existsByEmail(email);
    }
}
