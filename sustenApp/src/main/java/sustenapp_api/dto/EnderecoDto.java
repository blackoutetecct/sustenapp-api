package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;

import java.util.UUID;

@Data
@Builder
public class EnderecoDto {
    @NotNull @NotEmpty
    private String logradouro, complemento, cep, ciade, estado;

    @NotNull @UsuarioVerify
    private UUID usuario;
}
