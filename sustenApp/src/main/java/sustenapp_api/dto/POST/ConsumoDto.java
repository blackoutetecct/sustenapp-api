package sustenapp_api.dto.POST;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ConsumoDto {
    @NotNull
    private UUID recurso;

    @NotNull @NotEmpty @PositiveOrZero
    private Double consumo;
}
