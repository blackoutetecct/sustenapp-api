package sustenapp_api.dto.POST;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.EnderecoVerify;
import sustenapp_api.component.constraint.annotation.UsuarioVerify;

import java.util.UUID;

@Data
@Builder
@EnderecoVerify
public class EnderecoDto {
    @NotNull @NotEmpty
    private String logradouro, complemento, cep, cidade, estado;

    @NotNull @UsuarioVerify
    private UUID usuario;
}
