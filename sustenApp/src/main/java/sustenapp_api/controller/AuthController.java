package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.component.security.TokenService;
import sustenapp_api.dto.POST.AuthCheckDto;
import sustenapp_api.dto.POST.AuthDto;
import sustenapp_api.model.persist.UsuarioModel;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<String> findAuth(@RequestBody AuthDto usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(returnToken(usuario));
    }

    @PostMapping("/check")
    public ResponseEntity<TokenValid> verifyToken(@RequestBody AuthCheckDto token) {
        return ResponseEntity.status(HttpStatus.OK).body(checkToken(token.getToken()));
    }

    private String returnToken(AuthDto usuario) {
        return tokenService.generateToken(
                (UsuarioModel)
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha())
                        ).getPrincipal()
        );
    }

    private TokenValid checkToken(String token) {
        return new TokenValid(DateDependency.getDate(), token, !tokenService.validateToken(token).equals(""));
    }

    private record TokenValid(LocalDateTime time, String token, boolean valid) { }
}