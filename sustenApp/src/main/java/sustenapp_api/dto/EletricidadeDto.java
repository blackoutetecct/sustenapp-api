package sustenapp_api.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class EletricidadeDto {
    @NotNull
    private UUID usuario, tarifa;

    @NotNull
    private boolean renovavel;
}
