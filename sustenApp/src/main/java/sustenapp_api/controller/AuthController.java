package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.component.security.TokenService;
import sustenapp_api.dto.POST.AuthDto;
import sustenapp_api.model.persist.UsuarioModel;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("")
    public ResponseEntity<String> findAuth(@RequestBody @Valid AuthDto usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(returnToken(usuario));
    }

    private String returnToken(AuthDto usuario) {
        return tokenService.generateToken(
                (UsuarioModel)
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha())
                        ).getPrincipal()
        );

    }
}