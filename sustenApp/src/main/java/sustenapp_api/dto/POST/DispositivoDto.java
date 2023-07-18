package sustenapp_api.dto.POST;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.ComodoVerify;

import java.util.UUID;

@Data
@Builder
public class DispositivoDto {
    @NotNull
    private String nome;

    @NotNull @PositiveOrZero
    private int porta;

    @NotNull @ComodoVerify
    private UUID comodo;
}
