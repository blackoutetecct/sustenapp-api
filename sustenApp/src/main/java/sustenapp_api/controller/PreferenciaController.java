package sustenapp_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sustenapp_api.dto.POST.PreferenciaDto;
import sustenapp_api.dto.PUT.PreferenciaPutDto;
import sustenapp_api.model.persist.PreferenciaModel;
import sustenapp_api.service.PreferenciaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/preferencia")
@RequiredArgsConstructor
public class PreferenciaController {
    private final PreferenciaService preferenciaService;

    @PostMapping("")
    public ResponseEntity<PreferenciaModel> save(@RequestBody PreferenciaDto preferencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(preferenciaService.save(preferencia));
    }

    @PutMapping("")
    public ResponseEntity<PreferenciaModel> update(@RequestBody PreferenciaPutDto preferencia) {
        return ResponseEntity.status(HttpStatus.OK).body(preferenciaService.update(preferencia));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam UUID id) {
        preferenciaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("")
    public ResponseEntity<PreferenciaModel> findById(@RequestParam UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(preferenciaService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PreferenciaModel>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(preferenciaService.listAll());
    }
}