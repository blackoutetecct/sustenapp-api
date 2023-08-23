package sustenapp_api.dto.PUT;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RecursoPutDto {
    private UUID id;
    private UUID tarifa;
    private boolean renovavel;
    private String tipo;
}
