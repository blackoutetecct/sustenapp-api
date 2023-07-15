package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.TarifaVerify;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;

import java.util.UUID;

@Data
@Builder
public class RecursoDto {
    @NotNull @UsuarioVerify
    private UUID usuario;

    @NotNull @TarifaVerify
    private UUID tarifa;

    @NotNull
    private boolean renovavel;

    @NotNull @NotEmpty @Size(min = 7, max = 8)
    private String tipo;
}
