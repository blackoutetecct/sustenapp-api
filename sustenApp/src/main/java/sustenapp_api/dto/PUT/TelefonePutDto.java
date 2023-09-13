package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class TelefonePutDto {
    private UUID id;
    private String numero;
}
