package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.UsuarioDto;
import sustenapp_api.model.UsuarioModel;
import sustenapp_api.service.UsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/save")
    public ResponseEntity<UsuarioModel> save(@RequestBody @Valid UsuarioDto usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("")
    public ResponseEntity<UsuarioModel> update(@RequestBody UsuarioModel usuario) {
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

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioModel>> listAll(@RequestParam(required = false) boolean full) {
        if(full)
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listAllFull());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listAll());
    }
}
