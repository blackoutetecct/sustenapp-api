package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.model.type.RecursoTipo;
import sustenapp_api.repository.RecursoRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecursoValid {
    private final RecursoRepository recursoRepository;

    public boolean isValid(UUID usuario, String tipo) {
        return !existsRecurso(usuario, tipo);
    }

    private boolean existsRecurso(UUID usuario, String tipo){
        return recursoRepository.existsByUsuarioAndTipo(usuario, RecursoTipo.getRecurso(tipo));
    }
}
