package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import sustenapp_api.repository.ComodoRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class ComodoValidation {
    private final ComodoRepository comodoRepository;

    public boolean isValid(UUID value) {
        return existsComodo(value);
    }

    private boolean existsComodo(UUID comodo){
        return comodoRepository.existsById(comodo);
    }
}
