package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.POST.ComodoDto;
import sustenapp_api.dto.PUT.ComodoPutDto;
import sustenapp_api.model.persist.ComodoModel;
import sustenapp_api.service.ComodoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comodo")
@RequiredArgsConstructor
public class ComodoController {
    private final ComodoService comodoService;

    @PostMapping("")
    public ResponseEntity<ComodoModel> save(@RequestBody ComodoDto comodo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comodoService.save(comodo));
    }

    @PutMapping("")
    public ResponseEntity<ComodoModel> update(@RequestBody ComodoPutDto comodo) {
        return ResponseEntity.status(HttpStatus.OK).body(comodoService.update(comodo));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        comodoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<ComodoModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(comodoService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComodoModel>> listAllByUsuario(@RequestParam UUID usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(comodoService.listAllByUsuario(usuario));
    }
}
