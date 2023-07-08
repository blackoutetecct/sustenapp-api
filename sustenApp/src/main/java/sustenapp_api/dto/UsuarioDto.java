package sustenapp_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import sustenapp_api.component.constraint.annotation.StringVerify;

@Data
@Builder
public class UsuarioDto {
    @NotNull @NotEmpty @StringVerify
    private String nome;

    @NotNull @NotEmpty
    private String senha, tipo;

    @NotNull @Email
    private String email;

    @NotNull @CPF
    private String cpf;
}
