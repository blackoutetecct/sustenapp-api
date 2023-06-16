package sustenapp_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.DispositivoDto;
import sustenapp_api.model.DispositivoModel;
import sustenapp_api.service.DispositivoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dispositivo")
@RequiredArgsConstructor
public class DispositivoController {
    private final DispositivoService dispositivoService;

    @PostMapping("/save")
    public ResponseEntity<DispositivoModel> save(@RequestBody @Valid DispositivoDto dispositivo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dispositivoService.save(dispositivo));
    }

    @PutMapping("")
    public ResponseEntity<DispositivoModel> update(@RequestBody DispositivoModel dispositivo) {
        return ResponseEntity.status(HttpStatus.OK).body(dispositivoService.update(dispositivo));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        dispositivoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<DispositivoModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(dispositivoService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DispositivoModel>> listAllByUsuario(@RequestParam UUID usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(dispositivoService.listAllByUsuario(usuario));
    }
}
