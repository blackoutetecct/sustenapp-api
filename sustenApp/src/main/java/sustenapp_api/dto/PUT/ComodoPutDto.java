package sustenapp_api.dto.PUT;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;

import java.util.UUID;

@Data
@Builder
public class ComodoPutDto {
    @NotNull @NotEmpty
    private UUID id;

    @NotNull @NotEmpty
    private String nome;
}
