package sustenapp_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PreferenciaDto {
    @NotNull
    private boolean
            gerenciarEnergiaNaoRenovavel, gerenciarAguaNaoRenovavel,
            gerenciarEnergiaRenovavel, gerenciarAguaRenovavel;

    @NotNull
    private UUID usuario;
}