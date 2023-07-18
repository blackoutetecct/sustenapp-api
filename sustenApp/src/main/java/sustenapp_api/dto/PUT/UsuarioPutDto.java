package sustenapp_api.dto.PUT;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import sustenapp_api.component.constraint.annotation.CPFVerify;
import sustenapp_api.component.constraint.annotation.StringVerify;

import java.util.UUID;

@Data
@Builder
public class UsuarioPutDto {
    @NotNull @NotEmpty
    private UUID id;

    @NotNull @NotEmpty @StringVerify
    private String nome;

    @NotNull @NotEmpty
    private String senha;

    @NotNull @Email
    private String email;
}
