package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PreferenciaPutDto {
    private UUID id;

    private boolean
            gerenciarEnergiaNaoRenovavel, gerenciarAguaNaoRenovavel,
            gerenciarEnergiaRenovavel, gerenciarAguaRenovavel;
}