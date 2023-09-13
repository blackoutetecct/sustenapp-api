package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PreferenciaDto {
    private boolean
            gerenciarEnergiaNaoRenovavel, gerenciarAguaNaoRenovavel,
            gerenciarEnergiaRenovavel, gerenciarAguaRenovavel;
    private UUID usuario;
}