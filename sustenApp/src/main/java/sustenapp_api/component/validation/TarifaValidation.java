package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import sustenapp_api.repository.TarifaRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class TarifaValidation {
    private final TarifaRepository tarifaRepository;

    public boolean isValid(UUID value) {
        return existsTarifa(value);
    }

    private boolean existsTarifa(UUID tarifa){
        return tarifaRepository.existsById(tarifa);
    }


}
