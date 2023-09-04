package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.POST.UsuarioDto;
import sustenapp_api.dto.PUT.UsuarioPutDto;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.service.UsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<UsuarioModel> save(@RequestBody UsuarioDto usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("")
    public ResponseEntity<UsuarioModel> update(@RequestBody UsuarioPutDto usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(usuario));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<UsuarioModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @GetMapping("/email")
    public ResponseEntity<UsuarioModel> findByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByEmail(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioModel>> listAll(@RequestParam(required = false) boolean full) {
        if(full)
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listAllFull());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listAll());
    }
}
