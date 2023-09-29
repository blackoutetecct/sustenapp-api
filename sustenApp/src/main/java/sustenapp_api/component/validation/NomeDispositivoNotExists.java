package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.DispositivoRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NomeDispositivoNotExists {
    private final DispositivoRepository dispositivoRepository;

    public boolean isValid(UUID comodo, String nome) {
        return existsDispositivo(comodo, nome);
    }

    public boolean isValidPut(UUID dispositivo, String nome) {
        return existsDispositivoPut(dispositivo, nome);
    }

    private boolean existsDispositivoPut(UUID dispositivo, String nome){
        return !dispositivoRepository.existsByIdAndNome(dispositivo, nome);
    }

    private boolean existsDispositivo(UUID comodo, String nome){
        return !dispositivoRepository.existsByComodoAndNome(comodo, nome);
    }
}
