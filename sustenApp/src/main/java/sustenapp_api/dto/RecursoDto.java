package sustenapp_api.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class RecursoDto {
    @NotNull
    private UUID usuario, tarifa;

    @NotNull
    private boolean renovavel;

    @NotNull @NotEmpty @Size(min = 7, max = 8)
    private String tipo;
}
