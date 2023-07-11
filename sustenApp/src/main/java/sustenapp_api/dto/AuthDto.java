package sustenapp_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import sustenapp_api.component.constraint.annotation.UsuarioEmailVerify;

@Data
@Builder
public class AuthDto {
    @NotNull @Email @UsuarioEmailVerify
    private String email;

    @NotNull @NotEmpty
    private String senha;
}
