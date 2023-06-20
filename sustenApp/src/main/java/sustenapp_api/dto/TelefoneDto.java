package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TelefoneDto {
    @NotNull @NotEmpty
    private String telefone;

    @NotNull
    private UUID usuario;
}
