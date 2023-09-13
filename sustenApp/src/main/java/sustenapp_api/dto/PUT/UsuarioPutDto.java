package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UsuarioPutDto {
    private UUID id;
    private String nome;
    private String senha;
    private String email;
}
