package sustenapp_api.dto.POST;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;

import java.util.UUID;

@Data
@Builder
public class PreferenciaDto {
    @NotNull
    private boolean
            gerenciarEnergiaNaoRenovavel, gerenciarAguaNaoRenovavel,
            gerenciarEnergiaRenovavel, gerenciarAguaRenovavel;

    @NotNull @UsuarioVerify
    private UUID usuario;
}