package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.RecursoDto;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.service.RecursoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recurso")
@RequiredArgsConstructor
public class RecursoController {
    private final RecursoService recursoService;

    @PostMapping("")
    public ResponseEntity<RecursoModel> save(@RequestBody RecursoDto recurso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoService.save(recurso));
    }

    @PutMapping("")
    public ResponseEntity<RecursoModel> update(@RequestBody RecursoModel recurso) {
        return ResponseEntity.status(HttpStatus.OK).body(recursoService.update(recurso));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        recursoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<RecursoModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(recursoService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecursoModel>> listAllByUsuario(@RequestParam UUID usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(recursoService.listAllByUsuario(usuario));
    }
}
