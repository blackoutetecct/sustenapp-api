package sustenapp_api.dto.PUT;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class ComodoPutDto {
    private UUID id;
    private String nome;
}
