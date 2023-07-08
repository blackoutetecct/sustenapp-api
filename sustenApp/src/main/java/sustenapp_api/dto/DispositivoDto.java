package sustenapp_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.ComodoVerify;
import sustenapp_api.component.constraint.annotation.TarifaVerify;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;

import java.util.UUID;

@Data
@Builder
public class DispositivoDto {
    @NotNull
    private String nome;

    @NotNull @PositiveOrZero
    private int porta;

    @NotNull @UsuarioVerify
    private UUID usuario;

    @NotNull @TarifaVerify
    private UUID tarifa;

    @NotNull @ComodoVerify
    private UUID comodo;

    @NotNull
    private boolean renovavel;
}
