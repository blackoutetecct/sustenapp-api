package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.ComodoRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NomeComodoExists {
    private final ComodoRepository comodoRepository;

    public boolean isValid(UUID usuario, String nome) {
        return existsComodo(usuario, nome);
    }

    public boolean isValidPut(UUID comodo, String nome) {
        return existsComodoPut(comodo, nome);
    }

    private boolean existsComodoPut(UUID comodo, String nome){
        return !comodoRepository.existsByIdAndNome(comodo, nome);
    }

    private boolean existsComodo(UUID usuario, String nome){
        return !comodoRepository.existsByUsuarioAndNome(usuario, nome);
    }
}
