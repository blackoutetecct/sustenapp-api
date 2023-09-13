package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ComodoDto {
    private String nome;
    private UUID usuario;
}
