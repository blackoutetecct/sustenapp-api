package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class DispositivoDto {
    private String nome;
    private int porta;
    private UUID comodo;
}
