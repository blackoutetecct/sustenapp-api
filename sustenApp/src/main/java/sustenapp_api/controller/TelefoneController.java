package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.TelefoneDto;
import sustenapp_api.model.persist.TelefoneModel;
import sustenapp_api.service.TelefoneService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/telefone")
@RequiredArgsConstructor
public class TelefoneController {
    private final TelefoneService telefoneService;

    @PostMapping("")
    public ResponseEntity<TelefoneModel> save(@RequestBody TelefoneDto telefone) {
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneService.save(telefone));
    }

    @PutMapping("")
    public ResponseEntity<TelefoneModel> update(@RequestBody TelefoneModel telefone) {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.update(telefone));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        telefoneService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<TelefoneModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TelefoneModel>> listAllByUsuario(@RequestParam UUID usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.listAllByUsuario(usuario));
    }
}
