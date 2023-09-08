package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.ComodoRepository;
import sustenapp_api.repository.EnderecoRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EnderecoExists {
    private final EnderecoRepository enderecoRepository;

    public boolean isValid(UUID value) {
        return existsEndereco(value);
    }

    private boolean existsEndereco(UUID endereco){
        return enderecoRepository.existsById(endereco);
    }
}
