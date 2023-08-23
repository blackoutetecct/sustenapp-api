package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class EnderecoPutDto {
    private UUID id;
    private String logradouro, complemento, cep, cidade, estado;
}
