package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class EnderecoDto {
    @NotNull @NotEmpty
    private String logradouro, complemento, cep, ciade, estado;

    @NotNull
    private UUID usuario;
}
