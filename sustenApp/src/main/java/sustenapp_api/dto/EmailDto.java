package sustenapp_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDto {
    @NotNull @NotEmpty @Email
    String destinatario;

    @NotNull @NotEmpty
    String assunto, texto;
}
