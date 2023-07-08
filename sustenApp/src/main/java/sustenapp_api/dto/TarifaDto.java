package sustenapp_api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarifaDto {
    @NotNull @Positive
    private Double preco ;

    @NotNull @NotEmpty @Size(min = 7, max = 8)
    private String tipo;

    @NotNull @NotEmpty
    private String observacao;
}
