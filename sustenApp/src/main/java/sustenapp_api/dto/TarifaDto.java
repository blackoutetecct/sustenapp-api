package sustenapp_api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class TarifaDto {
    @NotNull @Positive
    private Double preco ;

    @NotNull @NotEmpty @Size(min = 7, max = 8)
    private String tipo;

    @NotNull @NotEmpty
    private String observacao;
}
