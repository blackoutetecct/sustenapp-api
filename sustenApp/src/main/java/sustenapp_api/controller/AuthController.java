package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.AuthDto;
import sustenapp_api.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${role.administrador.password}") private String administradorSenha;
    @Value("${role.suporte.password}") private String suporteSenha;
    @Value("${role.usuario.password}") private String usuarioSenha;

    private final UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<String> findAuth(@RequestBody @Valid AuthDto usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(verifyTipo(usuario));
    }

    private String verifyTipo(AuthDto usuario) {
        if(usuarioService.existsEmailAndSenhaAndTipo(usuario.getEmail(), usuario.getSenha(), usuario.getTipo()))
            return returnAuth(usuario.getTipo());

        return "{}";
    }

    private String returnAuth(String tipo) {
        String senha = switch (tipo){
            case "USUARIO" -> usuarioSenha;
            case "SUPORTE" -> suporteSenha;
            case "ADMINISTRADOR" -> administradorSenha;
            default -> "NULL";
        };

        return "{'username':'"+tipo+"','password':'"+senha+"'}";
    }
}