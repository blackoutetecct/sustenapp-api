package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.TelefoneRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TelefoneExists {
    private final TelefoneRepository telefoneRepository;

    public boolean isValid(UUID value) {
        return existsTelefone(value);
    }

    private boolean existsTelefone(UUID telefone){
        return telefoneRepository.existsById(telefone);
    }


}
