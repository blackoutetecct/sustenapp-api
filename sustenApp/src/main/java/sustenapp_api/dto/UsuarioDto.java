package sustenapp_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UsuarioDto {
    @NotNull @NotEmpty
    private String nome, senha;

    @NotNull @Email
    private String email;

    @NotNull @CPF
    private String cpf;
}
