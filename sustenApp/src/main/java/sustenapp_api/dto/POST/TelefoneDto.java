package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TelefoneDto {
    private UUID id;
    private String numero;
    private UUID usuario;
}
