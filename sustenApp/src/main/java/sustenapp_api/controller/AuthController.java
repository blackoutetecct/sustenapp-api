package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.AuthDto;
import sustenapp_api.service.AdministradorService;
import sustenapp_api.service.SuporteService;
import sustenapp_api.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${role.administrador.password}") private String administradorPassword;
    @Value("${role.suporte.password}") private String suportePassword;
    @Value("${role.usuario.password}") private String usuarioPassword;

    private final AdministradorService administradorService;
    private final SuporteService suporteService;
    private final UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<String> findAuth(@RequestParam String tipo, @RequestBody @Valid AuthDto usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(verifyTipo(tipo, usuario));
    }

    private String verifyTipo(String tipo, AuthDto usuario) {
        if(tipo.equals("administrador"))
            if(administradorService.existsEmailAndSenha(usuario.getEmail(), usuario.getSenha()))
                return returnAuth(tipo, administradorPassword);

        if(tipo.equals("suporte"))
            if(suporteService.existsEmailAndSenha(usuario.getEmail(), usuario.getSenha()))
                return returnAuth(tipo, suportePassword);

        if(tipo.equals("usuario"))
            if(usuarioService.existsEmailAndSenha(usuario.getEmail(), usuario.getSenha()))
                return returnAuth(tipo, usuarioPassword);

        return "{}";
    }

    private String returnAuth(String username, String password) {
        return "{'username':'"+username+"','password':'"+password+"'}";
    }
}