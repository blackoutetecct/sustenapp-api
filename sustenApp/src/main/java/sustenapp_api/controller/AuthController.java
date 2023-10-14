package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.component.dependency.TokenFactory;
import sustenapp_api.component.security.TokenService;
import sustenapp_api.component.validation.TokenValidation;
import sustenapp_api.dto.POST.AuthCheckDto;
import sustenapp_api.dto.POST.AuthDto;
import sustenapp_api.model.dynamic.TokenValidModel;
import sustenapp_api.model.persist.UsuarioModel;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenFactory tokenFactory;
    private final TokenValidation tokenValidation;

    @PostMapping("")
    public ResponseEntity<String> findAuth(@RequestBody AuthDto usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenFactory.createToken(usuario));
    }

    @PostMapping("/check")
    public ResponseEntity<TokenValidModel> verifyToken(@RequestBody AuthCheckDto token) {
        return ResponseEntity.status(HttpStatus.OK).body(tokenValidation.isValid(token));
    }
}