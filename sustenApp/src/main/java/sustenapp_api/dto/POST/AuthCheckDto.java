package sustenapp_api.dto.POST;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class AuthCheckDto {
    private String token;
}
