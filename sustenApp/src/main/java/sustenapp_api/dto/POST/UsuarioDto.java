package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    private String nome;
    private String senha, tipo;
    private String email;
    private String cpf;
}
