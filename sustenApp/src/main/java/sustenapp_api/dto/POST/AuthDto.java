package sustenapp_api.dto.POST;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    private String email;
    private String senha;
}
