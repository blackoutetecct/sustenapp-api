package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DispositivoPutDto {
    private UUID id;
    private String nome;
    private int porta;
    private UUID comodo;
}
