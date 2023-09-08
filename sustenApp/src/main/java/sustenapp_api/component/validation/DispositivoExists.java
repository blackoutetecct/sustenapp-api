package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.repository.DispositivoRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DispositivoExists {
    private final DispositivoRepository dispositivoRepository;

    public boolean isValid(UUID value) {
        return existsDispositvo(value);
    }

    private boolean existsDispositvo(UUID dispositivo){
        return dispositivoRepository.existsById(dispositivo);
    }
}
