package sustenapp_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class EletricidadeDto {
    @NotNull @NotEmpty @PositiveOrZero
    private Double consumo;

    @NotNull
    private UUID usuario, tarifa;

    @NotNull
    private boolean renovavel;
}
