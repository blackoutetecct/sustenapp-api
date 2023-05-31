package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ComodoDto {
    @NotNull @NotEmpty
    private String nome;

    @NotNull
    private UUID usuario;
}
