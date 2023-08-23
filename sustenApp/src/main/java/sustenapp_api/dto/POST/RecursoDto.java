package sustenapp_api.dto.POST;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RecursoDto {
    private UUID usuario;
    private UUID tarifa;
    private boolean renovavel;
    private String tipo;
}
