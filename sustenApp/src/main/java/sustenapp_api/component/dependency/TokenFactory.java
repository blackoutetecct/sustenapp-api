package sustenapp_api.component.dependency;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import sustenapp_api.component.security.TokenService;
import sustenapp_api.dto.POST.AuthDto;
import sustenapp_api.model.persist.UsuarioModel;

@Component
@RequiredArgsConstructor
public class TokenFactory {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public String createToken(AuthDto usuario) {
        return tokenService.generateToken(
                (UsuarioModel)
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha())
                        ).getPrincipal()
        );
    }
}
