package sustenapp_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecuperacaoDto {
    @NotNull @Email
    private String email;

    @NotNull @NotEmpty
    private String senha, codigo;
}
