package sustenapp_api.component.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.component.security.TokenService;
import sustenapp_api.dto.POST.AuthCheckDto;
import sustenapp_api.model.dynamic.TokenValidModel;

@Component
@RequiredArgsConstructor
public class TokenValidation {
    private final TokenService tokenService;

    public TokenValidModel isValid(AuthCheckDto token) {
        return checkToken(token.getToken());
    }

    private TokenValidModel checkToken(String token) {
        return new TokenValidModel(DateDependency.getDate(), token, !tokenService.validateToken(token).equals(""));
    }
}
