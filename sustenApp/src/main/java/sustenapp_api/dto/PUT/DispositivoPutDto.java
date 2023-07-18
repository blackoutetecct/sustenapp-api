package sustenapp_api.dto.PUT;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.ComodoVerify;

import java.util.UUID;

@Data
@Builder
public class DispositivoPutDto {
    @NotNull @NotEmpty
    private UUID id;

    @NotNull
    private String nome;

    @NotNull @PositiveOrZero
    private int porta;

    @NotNull @ComodoVerify
    private UUID comodo;
}
