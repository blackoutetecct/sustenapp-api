package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EnderecoDto {
    private String logradouro, complemento, cep, cidade, estado;
    private UUID usuario;
}
