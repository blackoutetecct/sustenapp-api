package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class ConsumoDto {
    @NotNull
    private UUID modalidade;

    @NotNull @NotEmpty @PositiveOrZero
    private Double consumo;
}
