package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecuperacaoDto {
    private String email;
    private String senha, codigo;
}
