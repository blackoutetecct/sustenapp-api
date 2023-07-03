package sustenapp_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.UUID;

@Data
public class DispositivoDto {
    @NotNull
    private String nome;

    @NotNull @PositiveOrZero
    private int porta;

    @NotNull
    private UUID usuario, tarifa, comodo;

    @NotNull
    private boolean renovavel;
}
