package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.PreferenciaRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PreferenciaExists {
    private final PreferenciaRepository preferenciaRepository;

    public boolean isValid(UUID value) {
        return existsPreferencia(value);
    }

    private boolean existsPreferencia(UUID preferencia){
        return preferenciaRepository.existsById(preferencia);
    }
}
