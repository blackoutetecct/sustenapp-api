package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.POST.DispositivoDto;
import sustenapp_api.dto.PUT.DispositivoPutDto;
import sustenapp_api.model.persist.DispositivoModel;
import sustenapp_api.service.DispositivoService;

import java.util.UUID;

@RestController
@RequestMapping("/dispositivo")
@RequiredArgsConstructor
public class DispositivoController {
    private final DispositivoService dispositivoService;

    @PostMapping("")
    public ResponseEntity<DispositivoModel> save(@RequestBody DispositivoDto dispositivo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dispositivoService.save(dispositivo));
    }

    @PutMapping("")
    public ResponseEntity<DispositivoModel> update(@RequestBody DispositivoPutDto dispositivo) {
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
}
