package sustenapp_api.dto.PUT;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TarifaPutDto {
    private UUID id;
    private Double preco ;
    private String observacao;
}
